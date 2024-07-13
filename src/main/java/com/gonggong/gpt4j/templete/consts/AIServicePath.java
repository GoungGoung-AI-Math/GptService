package com.gonggong.gpt4j.templete.consts;

public enum AIServicePath {
    CHAT_COMPLETE_PAHT("/v1/chat/completions"),
    EMBEDDING_PATH("/v1/embeddings");
    private String path;
    AIServicePath(String path){
        this.path = path;
    }
    @Override
    public String toString(){
        return path;
    }
}
