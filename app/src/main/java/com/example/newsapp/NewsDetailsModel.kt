package com.example.newsapp

import java.io.Serializable

data class NewsDetailsModel(
    val title: String,
    val author: String,
    val text: String,
) : Serializable

val newsList: ArrayList<NewsDetailsModel> = arrayListOf(
    NewsDetailsModel(
        "4 Republicans qualify for fourth 2024 presidential debate",
        "Daniel Strauss",
        """Four candidates have qualified for the fourth GOP presidential primary debate taking 
            |place Wednesday night in Alabama, the Republican National Committee and debate 
            |broadcaster NewsNation announced Monday.""".trimMargin()
    ),
    NewsDetailsModel(
        "GTA 6 leak: ‘Grand Theft Auto’ trailer reveals game’s release date",
        "Ramishah Maruf",
        """A trailer for “Grand Theft Auto VI,” the next installment of the wildly popular and 
            |violent game franchise, has been released early online after it had leaked. The 
            |trailer reveals that the sixth chapter is coming in 2025, according to developer 
            |Rockstar Games.""".trimMargin()
    ),
    NewsDetailsModel(
        "Richard Branson sends Virgin Galactic shares plunging after he says he’s not " +
                "putting any more money in",
        "Jackie Wattles",
        """Shares of Virgin Galactic, the space tourism venture founded by Richard Branson, are 
            |plunging after the British billionaire said he has no plans to invest more money in 
            |the company as he says it has “sufficient funds” already.""".trimMargin()
    ),
    NewsDetailsModel(
        "Will guilt-free long-haul flights ever be possible? Here’s what we know",
        "Jacopo Prisco",
        """Aviation faces a steep climb towards a greener future. Although it has, like many other 
            |industries, committed to slashing its planet-warming pollution by 2050, it is not on 
            |track to reach its target — mainly because there are no obvious ways to do 
            |so.""".trimMargin()
    ),
    NewsDetailsModel(
        "Gold has never been this expensive",
        "Anna Cooban",
        """Gold prices hit an all-time high Monday, buoyed by growing expectations of interest rate 
            |cuts among investors, a weaker dollar and geopolitical tensions.""".trimMargin()
    ),
    NewsDetailsModel(
        "Opinion: No one does capitalism like Taylor Swift",
        "Jeff Yang",
        """There’s a whole cottage industry in pop culture essay writing dedicated to trying to 
            |unpack why Taylor Swift is so successful. Is it because she’s the epitome of 
            |Americana? Or the ultimate vehicle for “millennial vibes”? Champion of the underdogs? 
            |The final girlboss?""".trimMargin()
    ),
)