package com.upao.renteasegrupo1.backingservice.mapper;
import com.upao.renteasegrupo1.backingservice.model.dto.*;
import com.upao.renteasegrupo1.backingservice.model.entity.Lodging;
import com.upao.renteasegrupo1.backingservice.model.entity.Payment;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class LodgingMapper {
    private final ModelMapper modelMapper;
    public Lodging convertToEntity(LodgingRequestDTO lodgingRequestDTO){
        return modelMapper.map(lodgingRequestDTO, Lodging.class);
    }

    public LodgingResponseDTO convertToDTO(Lodging lodging) {
        return modelMapper.map(lodging, LodgingResponseDTO.class);
    }
    public List<LodgingResponseDTO> convertToDTO(List<Lodging> lodgings){
        return lodgings.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public LodgingReportDTO convertLodgingToReportDTO(Object[] lodgingData){
        LodgingReportDTO reportDTO = new LodgingReportDTO();
        reportDTO.setTitle((String) lodgingData[0]);
        reportDTO.setDescription((String) lodgingData[1]);
        reportDTO.setCapacity((int) lodgingData[2]);
        reportDTO.setLocation((String) lodgingData[3]);
        return reportDTO;
    }
}
