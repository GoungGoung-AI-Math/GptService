package com.gonggong.gpt4j.domain.embedding;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PdfFileURLDto {
    private String url;
    private String pdfName; // 유니크 값.
}
