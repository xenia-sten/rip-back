package ru.stenyaeva.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.stenyaeva.back.domain.dto.folder.CreateFolderDto;
import ru.stenyaeva.back.domain.dto.folder.FolderDto;
import ru.stenyaeva.back.domain.utils.SecurityUtils;
import ru.stenyaeva.back.model.note.Folder;
import ru.stenyaeva.back.service.FolderService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/folders")
@CrossOrigin(methods = {RequestMethod.GET})
public class FolderController {
    private final SecurityUtils securityUtils;
    private final FolderService folderService;

    @GetMapping
    public List<FolderDto> getAll(){
        //return List.of(new FolderDto(1L,"",1L));
        return folderService.getAllByOwner(securityUtils.getUser()).stream().map(FolderDto::new).toList();
    }

    @GetMapping("/parentFolders")
    public List<FolderDto> getAllParentFolders(){
        return folderService.getParentFolders(securityUtils.getUser()).stream().map(FolderDto::new).toList();
    }

    @GetMapping("/{id}")
    public List<FolderDto> getAllByParent(@PathVariable("id") Long id){
        return folderService.getAllByParent(folderService.getById(id)).stream().map(FolderDto::new).toList();
    }

    @PostMapping
    public FolderDto createFolder(@RequestBody CreateFolderDto dto){

        Folder folder = new Folder(dto,
                securityUtils.getUser(),
                dto.getParent_id() == null ? null : folderService.getById(dto.getParent_id()));
        return new FolderDto(folderService.save(folder));
    }

    @PatchMapping
    public FolderDto updateFolder(@RequestBody CreateFolderDto dto, @RequestParam("id") Long id){
        Folder folder = new Folder(id, dto.getName(), securityUtils.getUser(),
                dto.getParent_id() == null ? null : folderService.getById(dto.getParent_id()));
        return new FolderDto(folderService.save(folder));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteFolder(@RequestParam("id") Long id){
        folderService.delete(id);
    }
}
