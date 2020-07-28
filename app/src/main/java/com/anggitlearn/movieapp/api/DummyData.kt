package com.anggitlearn.movieapp.api

import com.anggitlearn.movieapp.R

object DummyData {

    var titleMovie  = arrayOf(
        "A Rainy Day in New York",
        "Sonic the Hedgehog",
        "Ad Astra",
        "Avengers: Endgame"
    )

    var descMovie  = arrayOf(
        "Two young people arrive in New York to spend a weekend, but once they arrive they're met with bad weather and a series of adventures.",
        "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. Robotnik and his plans for world domination.",
        "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
        "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store."
    )

    var genreMovie  = arrayOf(
        "Comedy, Romance",
        "Comedy, Action, Family",
        "Drama, Advanture, Mystery",
        "Action, Time Travel, Avengers"
    )

    var posterMovie  = intArrayOf(
        R.drawable.ic_poster_a_rainy_day_in_new_york,
        R.drawable.ic_poster_sonic,
        R.drawable.ic_ad_astra,
        R.drawable.ic_avengers

    )

    var trailerMovie  = intArrayOf(
        R.raw.video_a_rainy_day,
        R.raw.video_sonic,
        R.raw.video_sample,
        R.raw.video_sample
    )

    var ratingMovie  = floatArrayOf(
        4.0F,
        3.0F,
        2.0F,
        5.0f
    )

}