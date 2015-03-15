package gh.funthomas424242.webapp.books.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DashBoardController {

	public DashBoardController() {

	}
	
	// Bücher: ->BookController
	
	@RequestMapping({ "/" })
	public ModelAndView listeBuecher() {
		return new ModelAndView("redirect:/books");
	}


	@RequestMapping("${link.settings}")
	public ModelAndView zeigeEinstellungen() {
		return new ModelAndView("einstellungen");
	}

	@RequestMapping("${link.user}")
	public ModelAndView zeigeNutzerdaten() {
		return new ModelAndView("user");
	}

	@RequestMapping("${link.impressum}")
	public ModelAndView zeigeImpressum() {
		return new ModelAndView("impressum");
	}

}
