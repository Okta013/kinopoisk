package ru.anikeeva.kinopoisk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anikeeva.kinopoisk.dto.CriticDTO;
import ru.anikeeva.kinopoisk.entities.Critic;
import ru.anikeeva.kinopoisk.repositories.CriticRepository;
import ru.anikeeva.kinopoisk.utils.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriticService {
    private final CriticRepository criticRepository;
    private final MappingUtils mappingUtils;

    @Autowired
    public CriticService(CriticRepository criticRepository, MappingUtils mappingUtils) {
        this.criticRepository = criticRepository;
        this.mappingUtils = mappingUtils;
    }

    public CriticDTO createCritic(CriticDTO criticDTO) {
        Critic critic = mappingUtils.mapToCritic(criticDTO);
        criticRepository.save(critic);
        return mappingUtils.mapToCriticDTO(critic);
    }

    public List<CriticDTO> getAllCritics() {
        return criticRepository.findAll()
                .stream().map(mappingUtils::mapToCriticDTO).collect(Collectors.toList());
    }

    public CriticDTO getCriticById(int id) {
        return mappingUtils.mapToCriticDTO(criticRepository.findById(id).orElse(new Critic()));
    }

    public CriticDTO updateCritic(int id, CriticDTO criticDTO) {
        Critic critic = criticRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Critic not found"));
        critic.setFirstName(criticDTO.getFirstName());
        critic.setSecondName(criticDTO.getSecondName());
        critic.setAbout(criticDTO.getAbout());
        criticRepository.save(critic);
        return mappingUtils.mapToCriticDTO(critic);
    }

    public void deleteCritic(int id) {
        criticRepository.deleteById(id);
    }
}
