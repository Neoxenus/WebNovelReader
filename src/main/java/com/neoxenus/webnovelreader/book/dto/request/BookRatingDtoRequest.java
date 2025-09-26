package com.neoxenus.webnovelreader.book.dto.request;

public record BookRatingDtoRequest(
        Integer storyDevelopment,
        Integer writingQuality,
        Integer worldBackground,
        Integer characterDesign
) {
    public int[] getArray(){
        return new int[]{
                this.storyDevelopment(),
                this.writingQuality(),
                this.worldBackground(),
                this.characterDesign()
        };
    }
}
