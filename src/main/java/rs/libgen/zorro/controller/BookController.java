package rs.libgen.zorro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.libgen.zorro.service.SearchService;
import rs.libgen.zorro.model.Book;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class BookController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search-by-title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title) throws IOException {
        return searchService.makePojoFromHtml(searchService.getBookRows(searchService.getUrlByTitle(title)));
    }
    @GetMapping("/res")
    public String resolveDownloadLink(@RequestHeader String url) throws IOException {
        System.out.println(searchService.downloadFileFromLink(searchService.resolveGetLinkMirror1(url)));
        return searchService.resolveGetLinkMirror1(url);
    }

}
