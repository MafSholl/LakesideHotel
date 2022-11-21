package com.lakesidehotel.app.users.service;

import com.lakesidehotel.app.admin.models.Admin;
import com.lakesidehotel.app.admin.repository.AdminRepository;
import com.lakesidehotel.app.guest.models.Guest;
import com.lakesidehotel.app.guest.repository.GuestRepository;
import com.lakesidehotel.app.manager.model.Manager;
import com.lakesidehotel.app.manager.repository.ManagerRepository;
import com.lakesidehotel.app.receptionist.models.Receptionist;
import com.lakesidehotel.app.receptionist.repository.ReceptionistRepository;
import com.lakesidehotel.app.room.exception.LakeSideHotelException;
import com.lakesidehotel.app.users.model.User;
import com.lakesidehotel.app.users.model.UserType;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.lakesidehotel.app.utils.ValidateEmail.isValidEmail;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private  final AdminRepository adminRepository;
    private final ReceptionistRepository receptionistRepository;
    private final ManagerRepository managerRepository;
    private final GuestRepository guestRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public String registerUser(RegisterRequest registerRequest) throws LakeSideHotelException {
        if (isValidEmail(registerRequest.getEmail())){
            if (registerRequest.getStaffType().equals(UserType.ADMIN)){
                return registerAdmin(registerRequest);
            }
            if (registerRequest.getStaffType().equals(UserType.MANAGER)){
                return registerManager(registerRequest);
            }
            if (registerRequest.getStaffType().equals(UserType.RECEPTIONIST)){
                return registerReceptionist(registerRequest);
            }
            throw new LakeSideHotelException("Unauthorized");
        }
        throw new LakeSideHotelException("This email is not valid");
    }

    private String registerReceptionist(RegisterRequest registerRequest) throws LakeSideHotelException {
        if (receptionistRepository.existsByEmail(registerRequest.getEmail())) throw new LakeSideHotelException("Receptionist with the same details already exist!!!");
        if (registerRequest.getConfirmPassword().equals(registerRequest.getPassword())){
            Receptionist receptionist = mapper.map(registerRequest, Receptionist.class);
            receptionist.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            receptionistRepository.save(receptionist);
            return receptionist.getFirstName() + " your registration was successful";
        }
        throw new LakeSideHotelException("Password does not match!!!");
    }

    private String registerManager(RegisterRequest registerRequest) throws LakeSideHotelException {
        if (managerRepository.existsByEmail(registerRequest.getEmail())) throw new LakeSideHotelException("Manager with the same details already exist!!!");
        if (registerRequest.getConfirmPassword().equals(registerRequest.getPassword())){
            Manager manager = mapper.map(registerRequest, Manager.class);
            manager.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            managerRepository.save(manager);
            return manager.getFirstName() + " your registration was successful";
        }
        throw new LakeSideHotelException("Password does not match!!!");
    }

    private String registerAdmin(RegisterRequest registerRequest) throws LakeSideHotelException {
        if (adminRepository.existsByEmail(registerRequest.getEmail())) throw new LakeSideHotelException("Admin with the same details already exist!!!");
        if (registerRequest.getConfirmPassword().equals(registerRequest.getPassword())){
            Admin newAdmin = mapper.map(registerRequest, Admin.class);
            newAdmin.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            adminRepository.save(newAdmin);
            return newAdmin.getFirstName() + " your registration was successful";
        }
        throw new LakeSideHotelException("Password does not match!!!");
    }

    @Override
    public String login(LoginRequest loginRequest) throws LakeSideHotelException {
        Optional<Admin> admin = adminRepository.findByEmail(loginRequest.getEmail());
        if (admin.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), admin.get().getPassword())) return "Welcome back " + admin.get().getFirstName();
        Optional<Receptionist> receptionist = receptionistRepository.findByEmail(loginRequest.getEmail());
        if (receptionist.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), receptionist.get().getPassword())) return "Welcome back " + receptionist.get().getFirstName();
        Optional<Manager> manager = managerRepository.findByEmail(loginRequest.getEmail());
        if (manager.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), manager.get().getPassword())) return "Welcome back " + manager.get().getFirstName();
        throw new LakeSideHotelException("Invalid login details!!!");
    }
    @Override
    public User getUserByUsername(String email) {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isPresent()) return admin.get();
        Optional<Manager> manager = managerRepository.findByEmail(email);
        if (manager.isPresent()) return manager.get();
        Optional<Guest> guest = guestRepository.findByEmail(email);
        if (guest.isPresent()) return guest.get();
        Optional<Receptionist> receptionist = receptionistRepository.findByEmail(email);
        if (receptionist.isPresent()) return receptionist.get();
        throw new UsernameNotFoundException("User not found");
    }
}
