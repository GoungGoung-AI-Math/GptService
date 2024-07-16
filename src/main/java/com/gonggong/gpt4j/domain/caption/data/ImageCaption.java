package com.gonggong.gpt4j.domain.caption.data;


import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "imagecaption")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ImageCaption {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @Column
    private String caption;

    @Basic
    @Type(JsonType.class)
    @Column(name = "embedding", columnDefinition = "vector")
    private List<Double> embedding;

    public static ImageCaption toEntity(ImageCaptionVo imageCaptionVo){
        return ImageCaption.builder()
                .name(imageCaptionVo.getName())
                .caption(imageCaptionVo.getCaption())
                .embedding(imageCaptionVo.getEmbedding())
                .build();
    }
}
