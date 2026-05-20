package com.example.logyssey.data

data class NewsItem(
    val id: Int,
    val title: String,
    val titleAr: String,
    val description: String,
    val descriptionAr: String,
    val photo : String
)

object NewsDataSource {
    val newsList = listOf(

        NewsItem(7, "Gamers frustrated as Hollow Knight: Silksong crashes stores on launch",
            "إحباط بين اللاعبين بعد تعطل المتاجر أثناء إطلاق Hollow Knight: Silksong",
            "The biggest online video game stores crashed on Thursday as they struggled to deal with high demand for Hollow Knight: Silksong - one of the most-anticipated games of the year.\n" +
                "\n" +
                "Thousands of users reported they were unable to buy the game on PC store Steam after its release at 15:00 BST, with errors persisting until around 17:30.\n" +
                "\n" +
                "Social media users also shared their difficulties trying to purchase Silksong on other platforms, including the Nintendo, PlayStation and Xbox game stores.\n" +
                "\n" +
                "Fans of Hollow Knight have been eagerly awaiting the sequel after the first game's release in 2017, which has sold more than 15 million copies worldwide according to the developer.",
            "تعطلت أكبر متاجر ألعاب الفيديو عبر الإنترنت يوم الخميس حيث واجهت صعوبة في التعامل مع الطلب المرتفع على لعبة Hollow Knight: Silksong - واحدة من أكثر الألعاب انتظاراً هذا العام.\n" +
                    "\nأبلغ آلاف المستخدمين عن عدم قدرتهم على شراء اللعبة من متجر Steam للحاسب الشخصي بعد إطلاقها في الساعة 15:00 بتوقيت BST، مع استمرار الأخطاء حتى حوالي الساعة 17:30.\n\nكما شارك مستخدمو وسائل التواصل الاجتماعي الصعوبات التي واجهوها في محاولة شراء Silksong على منصات أخرى، بما في ذلك متاجر ألعاب Nintendo و PlayStation و Xbox.\n\nوينتظر عشاق Hollow Knight الجزء الثاني بفارغ الصبر منذ إصدار اللعبة الأولى في عام 2017، والتي باعت أكثر من 15 مليون نسخة في جميع أنحاء العالم وفقاً للمطور.",
            "https://ichef.bbci.co.uk/news/1536/cpsprodpb/d305/live/d4dbcc00-89ac-11f0-978f-c724dfaf4309.png.webp")
    ,

        NewsItem(1, "Bloodborne video game film adaptation announced with YouTuber Jacksepticeye",
            "الإعلان عن تحويل لعبة الفيديو Bloodborne إلى فيلم بمشاركة اليوتيوبر Jacksepticeye",
            "Hit video game Bloodborne is being developed into an animated film co-produced by YouTube star Jacksepticeye.\n" +
                "\n" +
                "The 2015 PlayStation exclusive is one of the most celebrated games of all time, but has never received a sequel despite fan demand.\n" +
                "\n" +
                "While plot details and a release date remain under wraps, the film will be developed by Sony Pictures and PlayStation Productions, who say the adaption will be true to the spirit of the game.",
            "يتم حالياً تطوير لعبة الفيديو الشهيرة Bloodborne إلى فيلم رسوم متحركة من إنتاج مشترك لنجم اليوتيوب Jacksepticeye.\n\nتعد هذه اللعبة الحصرية لمنصة PlayStation التي صدرت عام 2015 واحدة من أكثر الألعاب احتفاءً على مر العصور، لكنها لم تحصل على جزء ثانٍ أبداً رغم مطالب المعجبين.\n\nوبينما لا تزال تفاصيل القصة وتاريخ الإصدار طي الكتمان، سيتم تطوير الفيلم من قبل Sony Pictures و PlayStation Productions، الذين يقولون إن الاقتباس سيكون مخلصاً لروح اللعبة.",

            "https://ichef.bbci.co.uk/news/1536/cpsprodpb/0fa3/live/576cd7f0-380c-11f1-ace0-77f4b81f6cfd.jpg.webp"),





        NewsItem(3, "Sophie Turner injury halts filming on Tomb Raider TV show",
            "إصابة صوفي تيرنر توقف تصوير مسلسل Tomb Raider",
            "Filming on the new Tomb Raider series has been paused after actress Sophie Turner sustained an injury, the show's bosses have confirmed.\n" +
                "\n" +
                "The Game of Thrones star plays heroine Lara Croft - a globe-trotting archaeologist - in the upcoming video game adaptation.\n" +
                "\n" +
                "A Prime Video spokesperson confirmed Turner had recently suffered a \"minor injury\", although didn't confirm if it happened on or off-set.\n" +
                "\n" +
                "\"As a precaution, production has briefly paused to allow her time to recover. We look forward to resuming production as soon as possible,\" they said.\n" +
                "\n" +
                "The streamer did not confirm how long the pause would last, nor did it give further details on the nature of the injury.",
            "أكد مسؤولو المسلسل أن تصوير مسلسل Tomb Raider الجديد قد توقف مؤقتاً بعد تعرض الممثلة صوفي تيرنر لإصابة.\n\nوتؤدي نجمة Game of Thrones دور البطولة بشخصية لارا كروفت - عالمة الآثار التي تجوب العالم - في الاقتباس القادم للعبة الفيديو.\n\nوأكد متحدث باسم Prime Video أن تيرنر عانت مؤخراً من \"إصابة طفيفة\"، رغم أنه لم يؤكد ما إذا كانت قد حدثت داخل موقع التصوير أم خارجه.\n" +
                    "\nوقالوا: \"كإجراء احترازي، توقف الإنتاج لفترة وجيزة لمنحها الوقت للتعافي. نتطلع إلى استئناف الإنتاج في أقرب وقت ممكن\".",
            "https://ichef.bbci.co.uk/news/1536/cpsprodpb/49cc/live/d6ea3620-2c2d-11f1-b8c9-f5058c0aec9a.jpg.webp"),

        NewsItem(
            4,
            "Elz The Witch: 'Gaming can help through tough times' ",
            "إلز ذا ويتش: 'الألعاب يمكنها المساعدة في الأوقات الصعبة'",
            "The gamaing influencer spoke to BBC Woman's Hour about how gaming has helped her through difficult times",
            "تحدثت مؤثرة الألعاب لبرنامج Woman's Hour في بي بي سي حول كيف ساعدتها الألعاب خلال الأوقات العصيبة والصعبة التي مرت بها.",
            "https://ichef.bbci.co.uk/news/800/cpsprodpb/a3d3/live/99e5b120-3ccf-11f1-879d-1b2f5c3919b8.jpg.webp"
        ),


                NewsItem(5,"Resident Evil Requiem's director on redefining the survival horror genre", "مخرج Resident Evil Requiem يتحدث عن إعادة تعريف نوع رعب البقاء",
                    "Thirty years ago, a game named Resident Evil ushered in the survival horror genre - a blend of nerve-shredding tension, elaborate puzzles, and intricate inventory management.\n" +
                        "\n" +
                        "The series became Japanese developer Capcom's best-selling franchise ever, prompting books, films and TV programmes, as well as more games.\n" +
                        "\n" +
                        "So how does the latest edition, Resident Evil Requiem - released to excited fans worldwide on Friday - keep the scares feeling surprising three decades on?\n"+
                "\n"+"producer Masato Kumazawa told the BBC the team has always been clear on Requiem's and Resident Evil's signature mood - fear.\n \"Fear is such a human emotion,\" he said.\n" +
                        "\n" +
                        "\"And through entertainment, we find ways to use fear as a thrill, but in a positive way.\"\n" +
                        "\n" +
                        "\"So I think that even with 30 years on the game's legacy, people still want to go through those thrills over and over again, because that's really what makes us human.\"",
                    "قبل ثلاثين عاماً، قدمت لعبة تدعى Resident Evil نوع رعب البقاء - وهو مزيج من التوتر الذي يحطم الأعصاب، والألغاز المعقدة، والإدارة الدقيقة للموارد.\n" +
                            "\nأصبحت هذه السلسلة هي الامتياز الأكثر مبيعاً للمطور الياباني Capcom على الإطلاق، مما أدى إلى إصدار كتب وأفلام وبرامج تلفزيونية، بالإضافة إلى المزيد من الألعاب.\n\nإذن كيف يحافظ الإصدار الأحدث، Resident Evil Requiem، على شعور الرعب المفاجئ بعد ثلاثة عقود؟\n" +
                            "\nأخبر المنتج ماساتو كومازاوا بي بي سي أن الفريق كان دائماً واضحاً بشأن الحالة المميزة لـ Requiem و Resident Evil - وهي الخوف. وقال: \"الخوف عاطفة بشرية أصيلة. ومن خلال الترفيه، نجد طرقاً لاستخدام الخوف كنوع من الإثارة، ولكن بطريقة إيجابية\".",
                    "https://ichef.bbci.co.uk/news/1536/cpsprodpb/017a/live/9f5c4cb0-1261-11f1-808c-3f897f9a068e.jpg.webp"),


                NewsItem(6,"PS5 price hiked by £90 due to global 'pressures'" ,
                    "رفع سعر جهاز PS5 بمقدار 90 جنيه إسترليني بسبب 'الضغوط' العالمية",
                    "Sony is raising the price of the PlayStation 5 by £90 in the UK and by \$100 in the US, in a dramatic hike it says is due to \"continued pressures in the global economic landscape\".\n" +
                        "\n" +
                        "The gaming giant said the changes, taking effect from 2 April, would see the recommended price for retailers of its PS5, PS5 Pro and PlayStation Portal handheld device rise around the world.\n" +
                        "\n" +
                        "It comes less than a year after the company hiked the price of the disc-free PS5 Digital Edition by £40, citing \"challenging\" market conditions.",
                    "تقوم شركة سوني برفع سعر جهاز PlayStation 5 بمقدار 90 جنيهاً إسترلينياً في المملكة المتحدة وبمقدار 100 دولار في الولايات المتحدة، في زيادة دراماتيكية تقول إنها بسبب \"الضغوط المستمرة في المشهد الاقتصادي العالمي\".\n\nوقالت عملاق الألعاب إن التغييرات، التي تدخل حيز التنفيذ اعتباراً من 2 أبريل، ستشهد ارتفاع السعر الموصى به لتجار التجزئة لأجهزة PS5 و PS5 Pro وجهاز PlayStation Portal المحمول في جميع أنحاء العالم.\n" +
                            "\nيأتي ذلك بعد أقل من عام من قيام الشركة برفع سعر نسخة PS5 الرقمية (بدون قرص) بمقدار 40 جنيهاً إسترلينياً، مشيرة إلى ظروف السوق \"الصعبة\".",
                    "https://ichef.bbci.co.uk/news/1536/cpsprodpb/d201/live/6d2be880-29f0-11f1-81d4-f1e3c77fafb4.jpg.webp"),

                        NewsItem(2, "Lake District disaster epic wins best British game",
                            "ملحمة الكوارث في منطقة 'ليك ديستريكت' تفوز بجائزة أفضل لعبة بريطانية",
        "A disaster epic set in the rolling hills of the Lake District won the award for best British game at the Bafta Games Awards.\n" +
                "\n" +
                "Atomfall, produced by Oxford-based Rebellion, is based on an alternative history of the UK's worst nuclear accident - the 1957 Windscale fire in Cumbria.\n" +
                "\n" +
                "It is set in a sci-fi inspired timeline where the area surrounding the plant has become a quarantine zone.",
                            "فازت ملحمة كوارث تقع أحداثها في تلال منطقة ليك ديستريكت بجائزة أفضل لعبة بريطانية في حفل جوائز بافتا للألعاب.\n\nلعبة Atomfall، التي أنتجتها شركة Rebellion ومقرها أكسفورد، تستند إلى تاريخ بديل لأسوأ حادث نووي في المملكة المتحدة - حريق ويندسكيل عام 1957 في كمبريا.\n" +
                                    "\nتقع أحداثها في خط زمني مستوحى من الخيال العلمي حيث أصبحت المنطقة المحيطة بالمصنع منطقة حجر صحي.",
        "https://ichef.bbci.co.uk/news/1536/cpsprodpb/4b6f/live/d7688110-0a5a-11f0-a11e-e35bec1405a8.jpg.webp"),

    )

}

fun NewsItem.getLocalizedTitle(lang: String): String {
    return if (lang == "ar") titleAr else title
}

fun NewsItem.getLocalizedDescription(lang: String): String {
    return if (lang == "ar") descriptionAr else description
}


