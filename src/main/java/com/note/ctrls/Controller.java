package com.note.ctrls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import com.note.entities.Note;
import com.note.services.GenericService;
import com.note.utils.Utils;

/**
 *
 * @author anupkalshetty
 */

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	GenericService<Note> genericService;

	@RequestMapping("/")
	public String home(ModelMap m) {
		List<Note> notes = genericService.getAllObjects();
		List<Note> redirectedNotes = (List<Note>) m.get("notes");
		if (CollectionUtils.isEmpty(redirectedNotes))
			m.put("notes", notes);
		else
			m.put("notes", redirectedNotes);
		return "index";
	}

	@RequestMapping("/reset")
	public String reset() {
		return "redirect:/";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute("note") Note n, RedirectAttributes red) {
		save(n);
		red.addFlashAttribute("note", n);
		return "redirect:/";
	}

	@RequestMapping("/edit/{nid}")
	public String edit(@PathVariable Long nid, RedirectAttributes ra) {
		Note n = (nid == null) ? new Note() : genericService.findById(nid);
		ra.addFlashAttribute("note", n);
		return "redirect:/";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("ids") List<Long> ids) {
		if (!CollectionUtils.isEmpty(ids)) {
			genericService.deleteById(ids);
		}
		return "redirect:/";
	}

	@RequestMapping("/find")
	public String find(@RequestParam("searchText") String searchText, RedirectAttributes red) {
		red.addFlashAttribute("notes", genericService.find(searchText));
		return "redirect:/";
	}

	private void save(Note n) {
		n.setText(HtmlUtils.htmlUnescape(n.getText()));
		Utils.setTitle(n);
		genericService.save(n);
	}

}
