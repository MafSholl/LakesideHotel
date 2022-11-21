package com.lakesidehotel.app.receptionist.service;


import com.lakesidehotel.app.receptionist.dto.ReceptionistDto;
import com.lakesidehotel.app.receptionist.models.Receptionist;
import com.lakesidehotel.app.receptionist.repository.ReceptionistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceptionistServiceImpl implements ReceptionistService{

    @Autowired
    private ReceptionistRepository receptionistRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ReceptionistDto createNewReceptionist(ReceptionistDto createReceptionistRequest) {
        Receptionist newReceptionist = modelMapper.map(createReceptionistRequest, Receptionist.class);
        Receptionist savedReceptionist = receptionistRepository.save(newReceptionist);
        return modelMapper.map(savedReceptionist, ReceptionistDto.class);
    }
}
