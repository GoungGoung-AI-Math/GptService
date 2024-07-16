package com.gonggong.gpt4j.domain.caption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageCaptionDto {
    private String name;
    private String caption;

    public static ImageCaptionDto toDto(ImageCaption imageCaption){
        return ImageCaptionDto.builder()
                .name(imageCaption.getName())
                .caption(imageCaption.getCaption())
                .build();
    }
}
