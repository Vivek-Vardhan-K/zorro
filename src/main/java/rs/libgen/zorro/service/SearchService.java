package rs.libgen.zorro.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.libgen.zorro.model.Book;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class SearchService {

    @Value("${TITLE_URL}")
    private String baseUrlForBook;

    @Value("${DOWNLOAD_LOCATION}")
    private String downloadLocation;

    public String getUrlByTitle(String bookName){
        bookName=bookName.replace(" ","%20");
        return baseUrlForBook.replace("||dynamix||",bookName);
    }

    public List<String> getBookRows(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
         String html=doc.getElementsByTag("tr").attr("valign","top").outerHtml();
         String[] htmlArr=html.split("<tr");
         Stream<String> st= Arrays.stream(htmlArr).skip(4);
         st=st.filter(s -> s.contains("pdf"));
         List<String> arr= new ArrayList<>();
         st.forEach(s-> arr.add(s));
         return arr;
    }


    public List<Book> makePojoFromHtml(List<String> stream){
        List<Book> ls=new ArrayList<>();
        stream.forEach(s->{
            Book book=new Book();
            String[] arr=s.split("<td");
            for(int i=1;i<arr.length;i++){
                String htm=arr[i];
                switch (i) {
                    case 1:
                        book.setId(arr[i].substring(arr[i].indexOf('>')+1,arr[i].indexOf('<')));
                        break;
                    case 2:
                        String ath="author\">";
                        int temp=arr[i].indexOf(ath)+ath.length();
                        if(arr[i].contains(ath))book.setAuthor(arr[i].substring(temp,htm.indexOf('<',temp)));
                        break;

                    case 3:
                        String ttl= book.getId()+"\">";
                        temp=htm.indexOf(ttl)+ttl.length();
                        if(htm.indexOf(ttl)!=-1)book.setTitle(htm.substring(temp,htm.indexOf('<',temp)));
                        break;
                    case 4:
                        book.setPublisher(htm.substring(htm.indexOf('>')+1,htm.indexOf('<')));
                        break;
                    case 5:
                        book.setYear(htm.substring(htm.indexOf('>')+1,htm.indexOf('<')));
                        break;
                    case 6:
                        book.setPages(htm.substring(htm.indexOf('>')+1,htm.indexOf('<')));
                        break;
                    case 7:
                        book.setLanguage(htm.substring(htm.indexOf('>')+1,htm.indexOf('<')));
                        break;
                    case 8:
                        book.setSize(htm.substring(htm.indexOf('>')+1,htm.indexOf('<')));
                        break;
                    case 9:
                        book.setExtension(htm.substring(htm.indexOf('>')+1,htm.indexOf('<')));
                        break;
                    case 10:
                        temp=htm.indexOf('"');
                        book.setMirror1(htm.substring(temp+1,htm.indexOf('"',temp+1)));
                        break;
                    case 11:
                        temp=htm.indexOf('"');
                        book.setMirror2(htm.substring(temp+1,htm.indexOf('"',temp+1)));
                        break;
                }
            }
            ls.add(book);
        });
        return ls;
    }

    public String resolveGetLinkMirror1(String url) throws IOException {
        log.info("resolving mirror Link");
        Document doc = Jsoup.connect(url).get();
        String ohml=doc.getElementsByTag("h2").outerHtml();
        int tmp=ohml.indexOf('"');
        String link=ohml.substring(tmp+1,ohml.indexOf('"',tmp+1));
        return link;
    }

    public String downloadFileFromLink(String bookUrl) throws IOException {
        Clock clock = Clock.systemDefaultZone();
        String fileName="req_book_14"+clock.millis()+".pdf";
        log.info("downloading file with temporary name : "+fileName);
        FileUtils.copyURLToFile(
                new URL(bookUrl),
                new File(fileName));
        return fileName;
    }
}
