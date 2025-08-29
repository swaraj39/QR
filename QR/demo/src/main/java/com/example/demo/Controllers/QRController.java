package com.example.demo.Controllers;

import com.example.qrdemo.qr.QRCodeGenerator;
import com.google.zxing.WriterException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class QRController {

    private String lastInput;

    @GetMapping("/")
    public String showForm() {
        System.out.println("Showing form in my way");
        return "form";
    }

    @PostMapping("/generate")
    public String generateQR(@RequestParam("info") String info, Model model) {
        this.lastInput = info;
        model.addAttribute("info", info);
        return "redirect:/show-qr";
    }

    @GetMapping("/show-qr")
    public String showQRPage(Model model) {
        model.addAttribute("info", lastInput);
        return "show-qr";
    }

    @GetMapping(value = "/qr-image", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] getQRImage() throws WriterException, IOException {
        return QRCodeGenerator.generateQRCodeImage(lastInput);
    }
}
