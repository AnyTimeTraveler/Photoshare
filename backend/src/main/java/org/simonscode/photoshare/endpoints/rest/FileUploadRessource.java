package org.simonscode.photoshare.endpoints.rest;

import org.simonscode.photoshare.entities.Photo;
import org.simonscode.photoshare.entities.PhotoResolution;
import org.simonscode.photoshare.entities.User;
import org.simonscode.photoshare.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController()
public class FileUploadRessource {

    private final StorageService storageService;
    private PhotoRepository photoRepository;

    @Autowired
    public FileUploadRessource(StorageService storageService, PhotoRepository photoRepository) {
        this.storageService = storageService;
        this.photoRepository = photoRepository;
    }

    @GetMapping(produces = "image/*", path = "/api/files/{fileId:\\d+}")
    @ResponseBody
    public void serveFile(@PathVariable Long fileId,
                          HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        Object user = request.getSession().getAttribute("user");
        if (user != null) {
            response.getOutputStream().write(storageService.getBytes(fileId));
            response.getOutputStream().flush();
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not logged in!");
        }
    }

    @PostMapping(path = "/api/files/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public void handleFileUpload(@RequestPart("file") MultipartFile file,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            PhotoResolution photoResolution = storageService.store(user.getId(), file.getBytes());
            Photo photo = new Photo();
            photo.getResolutions().add(photoResolution);
            photo.setFilename(file.getOriginalFilename());
            photo.setOwner(user);
            photoRepository.save(photo);
            response.sendError(HttpServletResponse.SC_CREATED, "{'result': 'You successfully uploaded " + file.getOriginalFilename() + "!'}");
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not logged in!");
        }
    }

}