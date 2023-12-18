package pt.gmartins.employee.manager.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pt.gmartins.employee.manager.domain.job.Job;
import pt.gmartins.employee.manager.domain.user.AddUserJobDTO;
import pt.gmartins.employee.manager.domain.user.UpdateUserDTO;
import pt.gmartins.employee.manager.domain.user.User;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import pt.gmartins.employee.manager.domain.user.UserDTO;
import pt.gmartins.employee.manager.services.UserService;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {
  private static String imageDirectory = System.getProperty("user.dir") + "/uploads/";

  private final UserService userService;

  public UserController(UserService userService){
    this.userService = userService;
  }


  @GetMapping
  private ResponseEntity<List<UserDTO>> getAllUsers(){
    return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
  }

  @PutMapping
  private ResponseEntity<User> updateUser(@RequestBody @Valid UpdateUserDTO data){
      User updatedUser = this.userService.updateUser(data);

      if (updatedUser != null){
        return ResponseEntity.ok(updatedUser);
      }else{
        return ResponseEntity.notFound().build();
      }
  }

  @DeleteMapping("/{id}")
  private ResponseEntity<?> deleteUser(@PathVariable UUID id){
    this.userService.deleteUser(id);

    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}/job")
  private ResponseEntity<Optional<Job>> getJobByUser(@PathVariable UUID id){
      return new ResponseEntity<>(this.userService.getJobByUser(id),HttpStatus.OK);
  }

  @PutMapping("/job")
  private ResponseEntity<User> addUserJob(@RequestBody @Valid AddUserJobDTO data){
      this.userService.addUserJob(data);
      return ResponseEntity.ok().build();
  }

  @RequestMapping(value = "/upload",produces = {MediaType.ALL_VALUE,"multipart/form-data"})
  public ResponseEntity<?> uploadImage(@RequestParam("imageFile")MultipartFile file){
    String name = "ola";

    makeDirectory(imageDirectory);
    Path fileNamePath = Paths.get(imageDirectory,
            name.concat(".").concat(Objects.requireNonNull(FilenameUtils.getExtension(file.getOriginalFilename()))));
    System.out.println(file);
    try {
      Files.write(fileNamePath,file.getBytes());
      return new ResponseEntity<>(name, HttpStatus.CREATED);
    }catch (IOException ex){
      return  new ResponseEntity<>("Image error",HttpStatus.OK);
    }
  }

  private void makeDirectory(String imageDirectory){
    File directory = new File(imageDirectory);
    if(!directory.exists()){
      directory.mkdir();
    }
  }

}
