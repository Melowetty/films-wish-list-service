package ru.melowetty.filmswishlistservice.model

enum class Rating {
    G,
    PG,
    PG6 {
        override fun toEnglishRating(): Rating {
            return G
        }
    },
    PG12 {
        override fun toEnglishRating(): Rating {
            return PG13
        }
    },
    PG13 {
        override fun toRussianRating(): Rating {
            return PG12
        }
    },
    PG16 {
        override fun toEnglishRating(): Rating {
            return R
        }
    },
    R {
        override fun toRussianRating(): Rating {
            return PG16
        }
    },
    PG18 {
        override fun toEnglishRating(): Rating {
            return NC17
        }
    },
    NC17 {
        override fun toRussianRating(): Rating {
            return PG18
        }
    },
    TVG {
        override fun toRussianRating(): Rating {
            return G
        }
    },
    TVPG {
        override fun toRussianRating(): Rating {
            return G
        }
    },
    TVY {
        override fun toRussianRating(): Rating {
            return G
        }
    },
    TVY7 {
        override fun toRussianRating(): Rating {
            return PG6
        }
    },
    TVFV {
        override fun toRussianRating(): Rating {
            return PG6
        }
    },
    TV14 {
        override fun toRussianRating(): Rating {
            return PG16
        }
    },
    TVMA {
        override fun toRussianRating(): Rating {
            return PG18
        }
    };

    open fun toEnglishRating(): Rating {
        return this
    };
    open fun toRussianRating(): Rating {
        return this
    }
}