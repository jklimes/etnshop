package cz.etn.etnshop.controller;

import cz.etn.etnshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("product/list");
        System.out.println("Count:" + productService.getProducts().size());
        modelAndView.addObject("test", "mytest");
        modelAndView.addObject("count", productService.getProducts().size());
        modelAndView.addObject("products", productService.getProducts());
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam("productId") int productId) {
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("product", productService.getProduct(productId));
        return modelAndView;
    }

    @RequestMapping("/update")
    public String update(@RequestParam("id") int productId, @RequestParam("name") String productName) {
        productService.updateProduct(productId, productName);
        return "redirect:/product/list";
    }

    @RequestMapping("/new")
    public ModelAndView editNew() {
        return new ModelAndView("product/new");
    }

    @RequestMapping("/addNew")
    public String addNew(@RequestParam("name") String productName, @RequestParam("serialNumber") String serialNumber) {
        productService.saveProduct(productName, serialNumber);
        return "redirect:/product/list";
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam("pattern") String pattern) {
        ModelAndView modelAndView = new ModelAndView("product/search");
        modelAndView.addObject("products", productService.findProducts(pattern));
        return modelAndView;
    }
}
