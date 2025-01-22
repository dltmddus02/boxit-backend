package com.box_it.box_it.controller;

import com.box_it.box_it.dto.EditProfileRequest;
import com.box_it.box_it.dto.ProfileResponse;
import com.box_it.box_it.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<ProfileResponse> editProfile(
            @PathVariable Long id,
            @RequestBody EditProfileRequest request
    ) {
        ProfileResponse profile = profileService.edit(
                id, request.getNickname(), request.getIntro());
        return ResponseEntity.ok(profile);
    }

}
