package rs.libgen.zorro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import rs.libgen.zorro.service.SearchService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebMvc
@EnableSwagger2
public class ZorroApplication {

	@Autowired
	private SearchService searchService;

	public static void main(String[] args) {
		SpringApplication.run(ZorroApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		List<Book> ls=searchService.makePojoFromHtml(searchService.getBookRows(searchService.getUrlByTitle("ice")));
//		ls.forEach(bk-> {
//			try {
//				searchService.resolveGetLink(bk.getMirror1());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		});
//		searchService.resolveGetLink("http://library.lol/main/C606487EDF34073C92C3B49DDDD40DE2");
//	}
}
