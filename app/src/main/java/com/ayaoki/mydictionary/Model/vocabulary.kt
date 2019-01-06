package com.ayaoki.mydictionary.Model

import java.util.*
import kotlin.collections.ArrayList

class Vocabulary (var meaning: String = "", var examples: ArrayList<String> = arrayListOf<String>(), var createdAt: Date = Date())

// めも　次のTODO
//- [ ] 例文用のviewを都度作成する(最初から作っておいて修正するのではなく。例文の数に上限を設けないため)
//- [ ] 詳細画面をざっくり作る