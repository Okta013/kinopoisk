package ru.anikeeva.kinopoisk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.anikeeva.kinopoisk.dto.CriticDTO;
import ru.anikeeva.kinopoisk.entities.Critic;
import ru.anikeeva.kinopoisk.services.CriticService;

import java.util.List;

@RestController
@RequestMapping("/api/critics")
public class CriticController {
    private final CriticService criticService;

    @Autowired
    public CriticController(CriticService criticService) {
        this.criticService = criticService;
    }

    @GetMapping
    public List<CriticDTO> getAllCritics() {
        return criticService.getAllCritics();
    }

    @GetMapping("/{id}")
    public CriticDTO getCriticById(@PathVariable int id) {
        return criticService.getCriticById(id);
    }

    @PostMapping("/new")
    public CriticDTO addCritic(@RequestBody CriticDTO criticDTO) {
        return criticService.createCritic(criticDTO);
    }

    @PutMapping("/{id}")
    public CriticDTO updateCritic(@PathVariable int id, @RequestBody CriticDTO criticDTO) {
        return criticService.updateCritic(id, criticDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCritic(@PathVariable int id) {
        criticService.deleteCritic(id);
    }
}
