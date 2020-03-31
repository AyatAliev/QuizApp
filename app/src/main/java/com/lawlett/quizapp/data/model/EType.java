package com.lawlett.quizapp.data.model;

import com.google.gson.annotations.SerializedName;

public enum EType {
    @SerializedName("multiple")
    MULTIPLE,
    @SerializedName("boolean")
    BOOLEAN
}
