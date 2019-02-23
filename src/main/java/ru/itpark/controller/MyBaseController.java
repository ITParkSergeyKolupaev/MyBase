package ru.itpark.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.itpark.dto.MyLinkEntityDto;
import ru.itpark.dto.MyMultimediaEntityDto;
import ru.itpark.dto.MyTextEntityDto;
import ru.itpark.service.MyBaseService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class MyBaseController {
    private final MyBaseService service;

    public MyBaseController(MyBaseService service) {
        this.service = service;
    }


    @GetMapping("/menu")
    public String getMenu(Model model) {
        model.addAttribute("itemsLink", service.getAllLink());
        model.addAttribute("itemsText", service.getAllText());
        model.addAttribute("itemsMultimedia", service.getAllMultimedia());
        return "menu";
    }


    @GetMapping("{id}/redirectLink")
    public String redirectExample(@PathVariable int id, HttpServletRequest request) {
        String link = service.getByIdLink(id).getLink();
        return "redirect:" + request.getScheme() +"://" +link;
    }

    //-----for Link
    @GetMapping("/{id}")
    public String getByIdLink(@PathVariable int id, Model model) {
        model.addAttribute("itemLink", service.getByIdLink(id));
        return "viewLink";
    }

    @GetMapping("/{id}/editLink")
    public String editLink(@PathVariable int id, Model model) {
        model.addAttribute("itemLink", service.getByIdOrEmptyLink(id));
        return "editLink";
    }

    @PostMapping("/{id}/editLink")
    public String editLink(@PathVariable int id, @ModelAttribute MyLinkEntityDto dtoLink) {
        service.saveLink(dtoLink);
        return "redirect:/menu";
    }

    @GetMapping("/{id}/removeLink")
    public String removeLink(@PathVariable int id, Model model) {
        model.addAttribute("itemLink", service.getByIdLink(id));
        return "remove";
    }

    @PostMapping("/{id}/removeLink")
    public String remove(@PathVariable int id) {
        service.removeByIdLink(id);
        return "redirect:/menu";
    }


    //-----for Text
    @GetMapping("/{id}/text")
    public String getByIdText(@PathVariable int id, Model model) {
        model.addAttribute("itemText", service.getByIdText(id));
        return "viewText";
    }

    @GetMapping("/{id}/editText")
    public String editText(@PathVariable int id, Model model) {
        model.addAttribute("itemText", service.getByIdOrEmptyText(id));
        return "editText";
    }

    @PostMapping("/{id}/editText")//дополнить
    public String editLink(@PathVariable int id, @ModelAttribute MyTextEntityDto dtoText) {
        service.saveText(dtoText);
        return "redirect:/menu";
    }

    @GetMapping("/{id}/removeText")
    public String removeText(@PathVariable int id, Model model) {
        model.addAttribute("itemText", service.getByIdText(id));
        return "remove";
    }

    @PostMapping("/{id}/removeText")
    public String removeText(@PathVariable int id) {
        service.removeByIdText(id);
        return "redirect:/menu";
    }


    //-----for Multimedia
    @GetMapping("/{id}/Multimedia")
    public String getByIdMultimedia(@PathVariable int id, Model model) {
        model.addAttribute("itemMultimedia", service.getByIdMultimedia(id));
        return "viewMultimedia";
    }

    @GetMapping("/{id}/editMultimedia")
    public String editMultimedia(@PathVariable int id, Model model) {
        model.addAttribute("itemMultimedia", service.getByIdOrEmptyMultimedia(id));
        return "editMultimedia";
    }

    @PostMapping("/{id}/editMultimedia")
    public String editMultimedia(@PathVariable int id, @ModelAttribute MyMultimediaEntityDto dtoMyMultimedia) {
        service.saveMultimedia(dtoMyMultimedia);
        return "redirect:/menu";
    }

    @GetMapping("/{id}/removeMultimedia")
    public String removeMultimedia(@PathVariable int id, Model model) {
        model.addAttribute("itemMultimedia", service.getByIdMultimedia(id));
        return "remove";
    }

    @PostMapping("/{id}/removeMultimedia")
    public String removeMultimedia(@PathVariable int id) {
        service.removeByIdMultimedia(id);
        return "redirect:/menu";
    }
}

