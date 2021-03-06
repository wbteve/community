package com.tongwii.dto.mapper;

import com.tongwii.domain.Residence;
import com.tongwii.dto.ResidenceDTO;
import com.tongwii.service.RegionService;
import com.tongwii.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 社区Dto、实体转换
 *
 * @author Zeral
 * @date 2017-11-24
 */
@Component
public class ResidenceMapper {
    private final UserService userService;
    private final RegionService regionService;

    public ResidenceMapper(UserService userService, RegionService regionService) {
        this.userService = userService;
        this.regionService = regionService;
    }

    public ResidenceDTO toDto(Residence residence) {
        if (residence == null) {
            return null;
        }

        ResidenceDTO residenceDTO = new ResidenceDTO();
        residenceDTO.setId(residence.getId());
        residenceDTO.setName(residence.getName());
        residenceDTO.setAddress(residence.getAddress());
        residenceDTO.setFloorCount(residence.getFloorCount());
        residenceDTO.setChargeUser(residence.getUserId());
        Optional.ofNullable(residence.getRegionCode()).ifPresent(regionCode -> residenceDTO.setRegion(regionService
            .findByRegionCode(regionCode)));

        return residenceDTO;
    }

   public Residence toEntity(ResidenceDTO residenceDTO) {
        if (residenceDTO == null) {
            return null;
        } else {
            Residence residence = new Residence();
            residence.setId(residenceDTO.getId());
            residence.setName(residenceDTO.getName());
            residence.setAddress(residenceDTO.getAddress());
            residence.setFloorCount(residenceDTO.getFloorCount());
            residence.setRegionCode(residenceDTO.getRegionCode());
            residence.setUserId(residenceDTO.getChargeUser());
            return residence;
        }
    }

    public List<ResidenceDTO> toDtos(List<Residence> residences) {
        return residences.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toList());
    }

    public Residence fromId(String id) {
        if (id == null) {
            return null;
        }
        Residence residence = new Residence();
        residence.setId(id);
        return residence;
    }
}
