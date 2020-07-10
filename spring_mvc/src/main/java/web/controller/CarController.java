package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.config.AppConfig;
import web.model.Car;
import web.service.CarService;
import web.service.CarServiceImp;

import java.util.List;

@Controller
public class CarController {

    private CarService carService = new CarServiceImp();
    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/cars")
    public String showCars(@RequestParam(value = "locale", required = false) String locale, ModelMap model, Model locMod) {
        List<Car> cars = carService.listCars();
        String result = "CARS";

        switch (locale) {
            case "ru":
                result = "МАШИНЫ";
                break;
            case "en":
                result = "CARS";
                break;
            default:
                result = "CARS";
                break;
        }

        locMod.addAttribute("test", result);
        model.addAttribute("cars", cars);
        return "car";
    }
}
