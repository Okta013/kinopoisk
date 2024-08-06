package ru.anikeeva.kinopoisk.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.anikeeva.kinopoisk.dto.CriticDTO;
import ru.anikeeva.kinopoisk.services.CriticService;

import java.util.List;

@Tag(name = "Критики", description = "CRUD-операции с критиками")
@RestController
@RequestMapping("/api/critics")
public class CriticController {
    private final CriticService criticService;

    @Autowired
    public CriticController(CriticService criticService) {
        this.criticService = criticService;
    }

    @Operation(
            summary = "Все критики",
            description = "Позволяет получить список всех критиков"
    )
    @GetMapping
    public List<CriticDTO> getAllCritics() {
        return criticService.getAllCritics();
    }

    @Operation(
            summary = "Просмотр критика",
            description = "Позволяет прочитать информацию о конкретном критике по id"
    )
    @GetMapping("/{id}")
    public CriticDTO getCriticById(@PathVariable int id) {
        return criticService.getCriticById(id);
    }

    @Operation(
            summary = "Создание критика",
            description = "Позволяет создать нового критика"
    )
    @PostMapping("/new")
    public CriticDTO addCritic(@RequestBody CriticDTO criticDTO) {
        return criticService.createCritic(criticDTO);
    }

    @Operation(
            summary = "Редактирование критика",
            description = "Позволяет отредактировать поля существующего критика по id"
    )
    @PutMapping("/{id}")
    public CriticDTO updateCritic(@PathVariable int id, @RequestBody CriticDTO criticDTO) {
        return criticService.updateCritic(id, criticDTO);
    }

    @Operation(
            summary = "Удаление критика",
            description = "Позволяет удалить существующего критика по id"
    )
    @DeleteMapping("/{id}")
    public void deleteCritic(@PathVariable int id) {
        criticService.deleteCritic(id);
    }
}
