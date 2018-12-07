package dsi.javanaise.controllers;

import dsi.javanaise.model.Javanaise;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.MissingResourceException;
import java.util.Set;


@RestController
public class JavanaiseController {

    private Set<Javanaise> dictionnaire = new HashSet<>();

    @GetMapping("/api/dictionary/javanais/v1.0/javanaises/{motFrancais}")
    public Javanaise getTranslation(@PathVariable("motFrancais") String motFrancais)
    {
        System.out.println("GET translation of " + motFrancais);

        for (Javanaise s : dictionnaire) {

            if(s.getMotFrancais().equals(motFrancais)) {
                return s;
            }
        }

        //404 not found
        throw new MissingResourceException(motFrancais, "Javanaise", "");
    }

    @GetMapping("/api/dictionary/javanais/v1.0/javanaises/dictionary")
    public ArrayList<Javanaise> getDictionary()
    {
        System.out.println("GET dictionary");

        ArrayList<Javanaise> dictionnaireList = new ArrayList<>(dictionnaire);

        return dictionnaireList;
    }

    @PostMapping("/api/dictionary/javanais/v1.0/javanaises")
    @ResponseStatus(HttpStatus.CREATED)
    public Javanaise createJavanaise(@RequestBody Javanaise mot)
    {
        if(mot.getMotFrancais().equals(""))
            throw new IllegalArgumentException();

        System.out.println("POST createJavanaise called " + mot);

        mot.translateToJavanaise();

        dictionnaire.add(mot);

        System.out.println("POST createJavanaise translation  " + mot.getMotJavanaise());

        return mot;
    }

    // Create Exception Handle
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
            reason = "Missing 'motFrancais' in body.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void motFrancaisExceptionHandler() {
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND,
            reason = "Not found motJavanais.")
    @ExceptionHandler(MissingResourceException.class)
    public void notFoundExceptionHandler()
    {
    }
}

