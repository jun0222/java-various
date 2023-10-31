package com.javarious.example.javavarious.domain.channels.model;

import lombok.Data;

// @Data は以下のメソッドを自動生成するLombokのアノテーション
// 各項目のgetter
// 各項目のsetter
// クラスの等価性を検証するequals
// クラスのハッシュ値を計算するhashCode
@Data
public class Channel {
    // チャンネルID
    private int id;

    // チャンネル名
    private String name;

}
