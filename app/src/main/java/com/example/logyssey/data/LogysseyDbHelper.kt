package com.example.logyssey.data


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.compose.ui.Modifier

class LogysseyDbHelper(context: Context) : SQLiteOpenHelper(context, "logyssey_database.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // tables 1  logyssey data
        db.execSQL(
            """
            CREATE TABLE games_table (
                id INTEGER PRIMARY KEY,
                isUpcoming INTEGER,
                title TEXT,
                Poster TEXT,
                releaseDate INTEGER,
                description TEXT,
                descriptionAR TEXT,
                PSrating REAL,
                Steamrating REAL,
                Genre TEXT,
                GenreAR TEXT,
                Similars TEXT, 
                status TEXT DEFAULT 'None',
                isFavorite INTEGER DEFAULT 0
            )
        """.trimIndent()
        )

        // Similars for games that show in "similar games row"
        //RE for resident evil , H for horro , TR tomb raider, sh for shooting  , S for souls and open world, SL for story line , B for bright , M multiplayer
        //P very popular F failed

// table 2 review data
        db.execSQL(
            """
            CREATE TABLE reviews_table (
                reviewId INTEGER PRIMARY KEY AUTOINCREMENT,
                gameId INTEGER,
                userRating REAL,
                reviewContent TEXT,
                hoursPlayed REAL,
                timestamp INTEGER,
                FOREIGN KEY(gameId) REFERENCES games_table(id) ON DELETE CASCADE
            )
        """.trimIndent()
        )

        seedInitialData(db)
    }

    // adding values to game_table
    private fun seedInitialData(db: SQLiteDatabase) {


        // new Games & popular

        db.execSQL(
            """
        INSERT INTO games_table VALUES (1, 0, 'Clair Obscur: Expedition 33', 
'https://image.api.playstation.com/vulcan/ap/rnd/202501/2217/e5833a53529ff9879e87689f1e8b04d45ba7e6c97fa791e2.png ' ,  
    1745483352000,
'Clair Obscur: Expedition 33 is a ground-breaking turn-based RPG with unique real-time mechanics, making battles more immersive and addictive than ever. Explore a fantasy world inspired by Belle Époque France in which you battle devastating enemies.
Once a year, the Paintress wakes and paints upon her monolith. Paints a cursed number. Everyone of that age turns to smoke and fades away. Year by year, that number ticks down and more of us are erased. Tomorrow she’ll wake and paint "33".
 Tomorrow we depart on our final mission - Destroy the Paintress, so she can never paint death again.
We are Expedition 33.
',
'قُد الرحلة، وحطّم الحلقة.
Clair Obskur: Expedition 33 هي لعبة تقمص أدوار رائدة قائمة على الأدوار مع آليات وقت فعلي تفاعلية فريدة، لتحظى بتجربة معارك غامرة للحواس وجذابة أكثر من أي وقت مضى. استكشف عالمًا خياليًا مستوحى من الحقبة الجميلة في فرنسا حيث تقاتل فيها أعداءً لدودين.

مرة في كل عام، تستيقظ الرسّامة وترسم على صخرتها الكبيرة. ترسم رقمًا ملعونًا. يتحول كل مَن يبلغ ذلك العمر إلى دخان ويتلاشى. عامًا بعد عام، يتناقص ذلك الرقم ويُمحى المزيد منا. ستستيقظ غدًا وترسم رقم "33". غدًا سننطلق في مهمتنا الأخيرة - تدمير الرسّامة، حتى لا تتمكن من رسم الموت مرة أخرى.

نحن Expedition 33.

',
4.84, 5.0, 'RPG, Action, Adventure', 'ألعاب تمثيل الأدوار , مغامرة' ,'SL, P', 'None', 0)
    """
        )
        // PRAGMATA
        db.execSQL(
            """
        INSERT INTO games_table VALUES (2, 0, 'PRAGMATA', 
'https://i.redd.it/scbz3ibc6f9e1.jpeg' ,
        
1776384000000, 
'" Wherever you go, I will be there. "

Pragmata is a unique, sci-fi action-adventure game from Capcom.

Follow Hugh, a member of an ill-fated investigation team, and Diana, a young android, as they navigate a lunar facility taken over by rogue AI in search of a way to Earth.\n" +

ONE TRAGIC ACCIDENT.
 AN UNLIKELY DUO.

At a remote lunar research facility, humanity discovers a new ore—one that, when properly refined, can be used to create almost anything using 3D printers.

When the facility suddenly falls silent, Hugh and his team are dispatched to investigate...but then disaster strikes.

Hugh is saved by Diana, a lone android wandering the complex. The two of them must join forces to find a way off the moon while working their way through an AI-controlled facility bent on stopping them.

Half-realized replicas of human civilization sprawl across the lunar surface, while inside, only scattered traces of the facility"s vanished inhabitants remain. What buried truth awaits at the end of Hugh and Diana"s journey? 
',
 '"أينما ذهبت، سأكون هناك."

لعبة Pragmata هي لعبة مغامرات وخيال علمي فريدة من نوعها من شركة Capcom.

اتبع هيو، أحد أعضاء فريق التحقيق المنكوب، وديانا، الفتاة الروبوت، أثناء تنقلهما في منشأة قمرية استولى عليها الذكاء الاصطناعي المارق بحثًا عن طريق إلى الأرض.

حادث مأساوي واحد. ثنائي غير متوقع.

في منشأة أبحاث قمرية نائية، تكتشف البشرية خامًا جديدًا، يمكن استخدامه، عند تكريره بشكل صحيح، لإنشاء أي شيء تقريبًا باستخدام الطابعات ثلاثية الأبعاد.

عندما يصمت المكان فجأة، يتم إرسال هيو وفريقه للتحقيق... ولكن بعد ذلك تقع الكارثة.

يتم إنقاذ هيو على يد ديانا، الروبوت الوحيد الذي يتجول في المجمع. يتعين على الاثنين توحيد قواهما للعثور على طريقة للخروج من القمر أثناء شق طريقهما عبر منشأة يتم التحكم فيها بواسطة الذكاء الاصطناعي بهدف إيقافهما.

تنتشر نسخ نصف مكتملة من الحضارة الإنسانية على سطح القمر، بينما في الداخل، لم يتبق سوى آثار متناثرة لسكان المنشأة المختفين. ما هي الحقيقة المدفونة التي تنتظرنا في نهاية رحلة هيو وديانا؟

التحكم بشخصيتين في نفس الوقت.

قم بتوجيه كل من هيو وهو يتحرك ويطلق النار ويقفز، وديانا التي تخترق في نفس الوقت.

استمتع بمزيج جديد ومبتكر من أسلوب اللعب في هذا النظام الفريد حقًا، والذي سيدفع عقلك إلى أقصى حدوده.
',

4.78, 5.0, 'sci-fi, Action,Adventure,Puzzle','حركة , الغاز','SL', 'None', 0)
    """
        )

        // COD
        db.execSQL(
            """
        INSERT INTO games_table VALUES (3, 0, 'Call of Duty®: Black Ops 7 - Cross-Gen Bundle', 
        'https://image.api.playstation.com/pr/bam-art/222/196/cd4b5ccb-6cd8-4566-a3fb-ca8b65b3a6fa.jpg?w=780&thumb=false', 
        1763164800000,
' In Call of Duty®: Black Ops 7, Treyarch and Raven Software are bringing players the biggest Black Ops ever. 

The year is 2035 and the world is on the brink of chaos, ravaged by violent conflict and psychological warfare. David Mason leads an elite team on a covert mission to the sprawling city of Avalon. While there, they discover a sophisticated plot that won’t just plunge the world into chaos, it will pull them into their own haunting pasts.

Squad up or go solo in an innovative Co-Op Campaign that redefines the Black Ops experience.

Multiplayer explodes out of the gate with 16 electrifying 6v6 maps and two 20v20 maps at launch. Master a cutting-edge arsenal and outmanoeuvre your enemies with a next level Omnimovement system.

In Treyarch’s legendary Round-Based Zombies mode, the nightmare begins where reality ends. Trapped in the heart of the Dark Aether, the crew is thrust into a vast, ever-shifting hellscape in the biggest Round-Based Zombies map in Black Ops history.
' ,
 '
يقدم Treyarch و Raven Software أكثر تجربة أخاذة ومذهلة لسلسلة Black Ops مع أكبر لعبة Call of Duty®: Black Ops 7.

إنه عام 2035 والعالم تغمره فوضى عارمة وتنهشه الصراعات العنيفة والحرب النفسية. في خضم هذا، يقود ديفيد مايسون فريقًا من النخبة من قيادة العمليات الخاصة المشتركة في مهمة سرية على الأراضي الشاسعة لمدينة أفالون الواقعة على البحر الأبيض المتوسط. في أثناء تواجدهم هناك، يكتشفون خطة معقدة ومحبوكة لن ترمي بالعالم نحو الهلاك فحسب، بل وستتلاعب بعقولهم لتعيد ماضيهم المرعب ليطاردهم.

كوّن فرقة أو اذهب منفردًا في طور قصة تعاوني يعيد تعريف تجربة لعب Black Ops.

طور اللعب الجماعي يأتي متألقًا وبقوة حيث يقدم 16 خريطة محتدمة 6 ضد 6 وخريطتين 20 ضد 20 عند الإصدار. أتقِن استخدام ترسانة أسلحة متطورة وتغلب على أعدائك مع نظام حرية الحركة.

في طور الزومبي القائم على الجولات، يبدأ الكابوس وتتلاشى الحقائق شيئًا فشيئًا. يُزج بالفريق إلى أرض جحيمية شاسعة ومتغيرة في قلب الأثير المظلم في أكبر خريطة زومبي قائم على الجولات في تاريخ Black Ops.
',

3.92, 2.5, 'Multiplayer, Shooting, Action','حركة,اكشن , قتال' ,'M,S', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (4, 1, 'Directive 8020 ', 
'https://pbs.twimg.com/profile_images/1900543405723148288/w_zcSyBf.jpg'
,1778560433000,
'From the creators of UNTIL DAWN and THE QUARRY, an all-new cinematic sci-fi horror adventure that blends intense survival gameplay with meaningful choices and a branching storyline.

Earth is dying and humanity is running out of time. 12 light years from home, Tau Ceti f offers a small sliver of hope. When the colony ship Cassiopeia crash lands on the planet, its crew soon realize they are far from being alone.

Hunted by an alien organism capable of mimicking its prey, the crew of the Cassiopeia must outwit their pursuers to make it home alive. As they battle to survive, they are confronted with the hardest choice of all: to save themselves, they must risk the lives of everyone on Earth.

  NEXT-GEN CINEMATIC SURVIVAL HORROR
Featuring Hollywood actor, Lashana Lynch (Bob Marley: One Love, The Woman King) as the ground-breaking astronaut, ‘Young’, experience immersive cinematic storytelling and edge-of-your-seat horror on a deep space mission to save humanity.

  REAL-TIME ALIEN THREATS
Evade a deadly alien threat that roams the dark corridors of the ship, intent on eradicating human life. Overcome your enemies using improvised weapons, lightning reflexes, and stealth.

  TRUST NO ONE
Encounter an alien lifeform that perfectly imitates its prey. Years of training and trust are lost among the crew when their enemy hides in plain sight. Who is human and who is not? Your choices are now more important than ever!

  REWIND DECISIONS
Rewrite your destiny and change the course of your story with our new Turning Points story tree. Uncover multiple endings, unlock hidden paths, and save your crew from their fateful deaths.

  DON’T PLAY ALONE
Recruit up to five players to join you on your mission in couch-co-op movie night mode. Choose which crew members to control and work together with your friends to survive alien infiltrators, deadly dilemmas, and catastrophic system failures.

',
'
قاتل للبقاء على قيد الحياة بينما يتسلل كائن فضائي إلى سفينتك... وطاقمك.
من مبتكري لعبتي Until Dawn و The Quarry تأتي مغامرة رعب الخيال العلمي السينمائية التي تجمع بين أسلوب اللعب المعتمد على النجاة في ظروف قاسية مع قصة متفرعة مليئة بالاختيارات المؤثرة.

الأرض تحتضر، ووقت البشرية ينفد. على بعد 12 سنة ضوئية من الوطن، تحمل Tau Ceti f خيط أمل رفيع بين جنباتها. عندما تحطمت سفينة المستعمرة Cassiopeia في أثناء الهبوط على الكوكب، أدرك طاقمها سريعًا أنهم ليسوا بمفردهم.

في ظل مطاردة من كائن فضائي قادر على محاكاة فريسته، يجب على طاقم سفينة Cassiopeia التفوّق في الذكاء على مطارديه للنجاة والعودة إلى أرض الوطن أحياء. بينما يقاتلون من أجل النجاة، يواجهون أصعب خيار على الإطلاق: لإنقاذ أنفسهم، عليهم أن يخاطروا بحياة جميع من على الأرض.
',

0.0, 0.0, 'Adventure, Horror, Decision Making ',' رعب, مغامرة ','SL ,H', 'None', 0)
    """
        )


        db.execSQL(
            """
        INSERT INTO games_table VALUES (5, 1, ' Beast of Reincarnation Digital ', 
'https://store-images.s-microsoft.com/image/apps.58975.13899657393549193.f3e8aa4e-b186-41e2-a641-ad8edf029915.af04eacb-fc42-4c87-9ea4-7b446b21f5c4'
,1785818033000,
'Set in a post-apocalyptic Japan, humanity"s only hope lies with Emma, an outcast shunned from society for her affliction, and Koo, the blighted dog. Together, they embark on this expansive adventure in this one-person, one-dog action RPG.

Experience a journey where you feel moments of loneliness, and also the reliability and comfort of your companion. Savor a world of impermanence, where forests suddenly burst forth amidst the wasteland. As Emma and Koo travel through this beautiful yet harsh world, their bond and abilities blossom.

Customize your playstyle with unique skill trees, gear, and spirit stones to survive this beautiful yet brutal world. Define your own playstyle through your preferred loadout, including ranged, stealthy, and aggressive combat.

In this world ravaged by blight... What will you find at the end of your journey?

Emma and Koo’s Synergy
Emma and Koo create a revolutionary action RPG experience. Fight as a unit with the protagonist, Emma’s sword abilities while giving Koo commands to unleash various techniques, just like in a turn-based RPG.

Japan: Year 4026
Travel through a beautiful yet harsh post-apocalyptic world. Experience the ever-changing scenery, in the blighted forest, and the Bosses that rule this world. Emma and Koo, who met in the far east, are tasked with battling Bosses from all over the world and capturing their powers in order to defeat the Beast of Reincarnation.

A story filled with secrets
Dive into a brutal narrative where every character hides a dangerous truth. Uncover the intertwined fates of Emma and Koo in this profound story.

',
'تدور أحداثها في ""اليابان"" ما بعد وقوع كارثة، حيث يكون أمل البشرية الوحيد معلقًا على Emma، المنبوذة التي نبذها المجتمع بسبب علّتها، وKoo، كلب من blight. يشرعان معًا في هذه المغامرة الواسعة داخل لعبة تقمص الأدوار والحركة هذه التي تتمحور حول بطلة واحدة وكلب واحد.

استمتع برحلة حيث تشعر بلحظات الوحدة وأيضًا الموثوقية والراحة لوجود رفيقك. خُض غمار عالم زائل، حيث تظهر الغابات فجأة وسط الأراضي القاحلة. بينما تسافر Emma وKoo في هذا العالم الجميل والقاسي في آن واحد، تزدهر علاقاتهما وقدراتهما.

خصّص أسلوب لعبك مع أشجار المهارة الفريدة والعتاد وspirit stones للبقاء على قيد الحياة في هذا العالم الجميل والوحشي في آن واحد. حدد أسلوب لعبك خلال التحميل المفضل لديك، بما في ذلك القتال الهجومي والمتخفي والمدافع.

في هذا العالم الذي اجتاحه blight... ما الذي ستعثر عليه في نهاية رحلتك؟

تعاون Emma وKoo
تعمل Emma وKoo على خوض تجربة لعبة تقمص الأدوار والحركة الثورية. قاتل كوحدة مع البطلة، وقدرات سيف Emma مع إعطاء Koo الأوامر لإطلاق العنان لتقنيات مختلفة، تمامًا كما في لعبة تقمص الأدوار القائمة على الأدوار.

اليابان: العام 4026
سافر في عالم ما بعد وقوع الكارثة الجميل والقاسي في آن واحد. استمتع بالمناظر المتغيرة على الدوام، في غابة blight، والزعماء الذي يسيطرون على العالم. ',
0.0, 0.0, 'Action, RPG ','حركة','SL', 'None', 0)
    """
        )



        db.execSQL(
            """
        INSERT INTO games_table VALUES (6, 0, ' Until Dawn ', 
'https://m.media-amazon.com/images/M/MV5BMGQwMzA4ODctN2VjNi00NTk2LWE5NzItOWJlYmM3NWUyNjZjXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg
',1445056433000,
'  When eight friends return to the isolated lodge where two of their group vanished a year prior, fear tightens its icy grip, and their mountain retreat descends into a nightmare with no escape.
Face your fears and determine who survives Until Dawn in this seminal horror classic, rebuilt and enhanced for PS5® consoles.

   Cult horror remade
Immerse yourself in a gripping slasher horror rebuilt from the ground up with stunning visuals in Unreal Engine 5. Enhanced by movie-like cinematography, refined gameplay mechanics and more, venture into a thrilling exploration of an isolated mountain where nothing is as it seems.

  Their fate in your hands
Create your own gripping tale as you control a group of unique characters portrayed by an all-star cast, including Hayden Panettiere, Brett Dalton, and more, and decide their fate through your choices. Through dynamic animations and nuanced facial-capture performances, learn what makes each character tick, and who among the group has what it takes to survive.

  Terror never looked so good
Explore a rich and detailed environment that will leave you breathless with every step. With new visual enhancements for PS5® consoles, the original game has been rebuilt from the ground up – utilizing cutting-edge visual technology to bring the nightmare to life.
',
'قم بتنزيل Until Dawn لتتعرف على الماضي الغامض لمجموعتك .خلال الفصل الإضافي في القصة التي تعيشها.

وحدها اختياراتك ستحدد من يبقى على قيد الحياة

عندما يقوم أصدقاؤك الثمانية بالعودة إلى مسكنهم عند الجبل -حيث اختفى اثنان من مجموعتهم منذ عام- ستصبح الأحداث مليئة بالخطر والغموض.

وفي حالة من الخوف والتوتر، سيكون عليك اتخاذ قرارات صعبة سيتحدد بناءً عليها مصير كل شخص في مجموعتك؛ قراراتك هي التي ستحدد موتهم أو بقائهم على قيد الحياة.
',
4.5, 4.5, 'Horror, Decision Making ','رعب, مغامرة', 'SL','None', 0)
    """
        )


// start here
        db.execSQL(
            """
        INSERT INTO games_table VALUES (7, 0, 'Elden Ring Nightreign', 
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTo2kOI9lwsrPOWZ32hGODRreFVkC2lEBTioUiENtS7qTCtdtnTTt5TlS72CI7pH3YKBVY_zPzyKXhPFosTyhD21FLdk0_sHeIPYI5n5DN2WQ&s=10', 
        1748593186000,
'Are you ready for the next standalone adventure within the Elden Ring universe?

Crafted by FromSoftware, Elden Ring Nightreign invites you on fierce, multiplayer action adventures across three in-game days, featuring a series of magnificent bosses.

Gather powerful weapons, survive the first two nights with your other two team members, and defeat the big boss on the final night. Take on foes with fellow heroes and make each night count.
',
'
تُعد ELDEN RING NIGHTREIGN مغامرة مستقلة داخل عالم ELDEN RING، صُممت لتقدم تجربة لعب جديدة إلى اللاعبين من خلال إعادة تصور التصميم الأساسي للعبة.

انتصروا معًا
تحالَف مع لاعبين آخرين لمواجهة الظلام الزاحف وما يحمله من مخاطر في تجربة لعب تعاونية مكونة من 3 لاعبين.

كُن بطلًا
قُد أبطالًا يتسمون بمهارات فريدة، إذ يمتلك كل منهم قدراته الخاصة وأسلوبه المميز. رغم قوة كل فرد على حدة، فإن اتحاد مهاراتهم حين يتعاونون يشكّل تآزرًا باطشًا.

اقضِ على الظلام
تغلب على تهديد بيئي متواصل يجتاح أرضًا تتغير باستمرار بين كل جلسة لعب وأخرى، واهزم زعيم هذا الظلام الهائل!
',
4.17, 4.5, 'Dungeon crawl, Action RPG,Fighting,Adventure,Survival','حركة', 'S', 'None', 0)
    """
        )


        //RES


        db.execSQL(
            """
        INSERT INTO games_table VALUES (8, 0, 'Resident Evil Requiem', 
'https://image.api.playstation.com/vulcan/ap/rnd/202512/1205/79661d7a2bdb9784749b4e57e1456ca89f7ac7bed8615aee.png
'
,
1772180952000,
'Prepare to escape death in a heart-stopping experience that will chill you to your core.

A new era of survival horror arrives with Resident Evil Requiem, the ninth title in the mainline Resident Evil series. Experience terrifying survival horror with FBI analyst Grace Ashcroft, and dive into pulse-pounding action with legendary agent Leon S. Kennedy. Both of their journeys and unique gameplay styles intertwine into a heart-stopping, emotional experience that will chill you to your core.

Experience the series classic survival horror through combat, investigations, puzzles and resource management. Gameplay allows you to freely switch between first and third-person views to face the horrors in a way that suits your playstyle.
',
'عصر جديد من رعب البقاء يبدأ مع Resident Evil Requiem، أحدث إصدار في سلسة Resident Evil وأكثرهم إنغماساً بتجربة أيقونية . عش تجربة رعب البقاء تخطف الأنفاس مع محللة مكتب التحقيقات الفيدرالي "غريس آشكروفت"، وانغمس في عالم الإثارة والتشويق مع العميل الأسطوري "ليون إس. كينيدي". تتشابك رحلتهما الفريدة وأسلوب لعبهما المميز في تجربة عاطفية آسرة و تشعرك بالقشعريرة حتى الأعماق.

ملاحظة: هذا المحتوى متاح أيضًا كجزء من حزمة واحدة أو أكثر. يُرجى تفقّد عمليات شرائك السابقة لتجنب الحصول على عناصر مكررة.
',
4.4, 5.0, 'Survival horror, Adventure','حركة , رعب, مغامرة' ,'RE, H' ,'None', 0)
    """
        )



        db.execSQL(
            """
        INSERT INTO games_table VALUES (9, 0, 'Resident Evil 2 REmake', 
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeh_Q0UXxfieJYL8GTx0GHzT2H1mq8DZv_L64t2VfA2ODI2r2uIQeim88T7XJcQopMFYbdm_yBVYwrfogmzFwZXy-zkpyVXpE7AY-OEywx&s=10',
1548404952000,
'Originally released in 1998, Resident Evil 2, one of the most iconic games of all time, returns completely reimagined for next-gen consoles.

Play individual campaigns for both Leon Kennedy and Claire Redfield using an all new 3rd person view as you explore the zombie infested areas of Raccoon City, now stunningly re-built using Capcom’s proprietary RE Engine. New puzzles, storylines and areas mean both new and seasoned fans will find horrifying new surprises await them!
',
'إذا كنت تمتلك نسخة PS4™‎ بالفعل لهذه اللعبة، فسيمكنك الحصول على نسخة PS5™‎ الرقمية دون تكلفة إضافية ولن تحتاج إلى شراء هذا المنتج. يجب على مالكي نسخة القرص من PS4™‎ إدخالها في PS5™‎ كلما كانوا يرغبون في تنزيل نسخة PS5™‎ الرقمية أو تشغيلها. لن يتمكن المالكون لقرص اللعبة المخصص لـ PS4™‎، الذين يشترون جهاز PS5™‎ الإصدار الرقمي الذي يعمل دون أقراص من الحصول على نسخة PS5™‎ دون تكلفة إضافية.

طُرحت Resident Evil 2 أولاً في عام 1998، ويُعاد تصوّرها كليًّا كواحدة من أكثر الألعاب شهرة في كل العصور لتلائم الجيل القادم من منصات أجهزة ألعاب الفيديو.

يمكنك لعب وضع القصة الفردية لكل من Leon Kennedy و Claire Redfield مستخدمًا منظور الشخص الثالث بينما تستكشف المناطق التي يتفشى فيها الموتى الأحياء في Raccoon City، والتي تظهر الآن بإطلالة مُعاد بناؤها باستخدام RE Engine التابعة لملكية Capcom. ويعني استحداث ألغاز وتطورات للقصة ومناطق أن ثمة مفاجآت مرعبة في انتظار الجماهير والمحنكين على حد سواء
',

4.77, 5.0, 'Survival horror, Puzzle, Shooter ','
رعب, مغامرة, حركة','RE,H', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (11, 0, 'Resident Evil 4 REmake', 
'https://image.api.playstation.com/vulcan/ap/rnd/202207/2509/85p2Dwh5iDhUzRKe40QeNYh3.png 
'
,1679646552000,
'Resident Evil 4, 2005’s legendary survival horror, is brought fully up-to-date in this ground-up remake.

Six years after the events of Resident Evil 2, Raccoon City survivor Leon Kennedy has been dispatched to a secluded European village to investigate the disappearance of the US president’s daughter. What he discovers there is unlike anything he has faced before.

Every aspect of the classic game has been updated for the current generation, from modernized graphics and controls, to a reimagined storyline that may surprise even hardened fans of the original game.
',
'ما النجاة إلا مجرد بداية.

مضت ستة أعوام منذ أن وقعت كارثة بيولوجية في مدينة راكون. أُرسل العميل ليون س. كينيدي، أحد الناجين من الحادث لإنقاذ ابنة الرئيس المخطوفة. تعقبها إلى قرية أوروبية نائية يعاني السكان المحليون فيها من خطب مريع ثم يُرفع الستار عن قصة الإنقاذ الجرئ والرعب القاسي حيث تلتقي الحياة بالموت والرعب والحزن.

تشمل Resident Evil 4 أسلوب لعب حديث وقصة أُعيد تصورها ورسومات ذات تفاصيل حية كما تبرز عودة أحد أفضل الألعاب في المجال.

عش الكابوس الذي أحدث ثورة في ألعاب النجاة والرعب.
',
4.8, 5.0, ' Survival horror, Puzzle,Third-person shooter,Adventure','
رعب, مغامرة, حركة','RE', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (10, 0, 'Resident Evil 3 REmake', 
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmUaOd2urwEzRCltpteMhicYRXDj-c4QgcCKRyv0rvmldMGZMa9wnbWXt5PAe5O1iq7upkoMByeo2h4Vy-kWhChiHprDnSMiuSrcuuZubX&s=10',
1585902552000,
'Relive the horrifying downfall of Raccoon City in a stunning reimagining of the 1999 survival horror classic.

In the nightmarish hours leading up to and following the events of Resident Evil 2, only you can help surviving S.T.A.R.S. officer Jill Valentine escape a city beset by a gruesome viral outbreak.

But zombies aren’t the only threat out for your blood. The towering, near-indestructible bioweapon, Nemesis T-Type, is also on the hunt. This brutal monstrosity uses an arsenal of high-powered weaponry to relentlessly pursue you across Raccoon City, meaning nowhere is truly safe.

Featuring cutting-edge presentation and revamped action-adventure gameplay, Resident Evil 3 updates the unforgettable Raccoon City story arc finale in explosive fashion. ',
'جيل فالنتين أحد آخر الأشخاص الباقين في مدينة راكون وتشاهد فظائع أمبريلا. لإيقافها، تطلق أمبريلا سلاحها السري المُطلق؛ نيمسيس!


تتضمن Resident Evil Resistance،صدار PS4 ™) لعبة جماعية عبر الإنترنت ذات طور 1 مقابل 4 تقع أحداثها في عالم Resident Evil حيث يواجه أربعة ناجين العقل المدبر الشرير.',
4.42, 4.5, 'Survival horror, Puzzle, Third-person shooter','مغامرة, حركة','RE', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (37, 0, 'Resident Evil 7: Biohazard', 
'https://www.wingamestore.com/images_boxshots/master/resident-evil-7-biohazard-1576219653.jpg
' , 
1485230178000,
'Menace and isolation seethe through the rotting walls of an abandoned farmhouse in the American South. 7 marks a new beginning for survival horror, and a full model change to the frightening and immersive "Isolated View "player perspective. 

Powered by the cutting-edge RE Engine, horror reaches new heights of photorealism so overwhelming you won"t be able to turn away. Enter a terrifyingly new world of horror and survive.
',
'الخطر والعزلة يسريان على كل جزء من الجدران العفنة لمنزل ريفي مهجور في الجنوب الأمريكي. الرقم 7 يسجل بدايةً جديدةً لرعب البقاء وتغييرًا نموذجيًا كاملاً على منظور اللاعب من "العرض المنفصل" المرعب والمجسم. مدعومًا بمحرك RE المتطور، يصل الرعب إلى آفاق جديدة من التصوير الواقعي، آفاق عالية جدًا لحد لن يسمح لك بأن تصرف نظرك عنها. ادخل عالمًا جديدًا رائعًا من الرعب وابق حيًا.

تدعم Resident Evil 7™ biohazard خاصية PlayStationⓇVR للحصول على أفضل تجربة جذابة لرعب البقاء.',
4.64, 4.5, 'Survival horror, Puzzle, Action','رعب, مغامرة, حركة','RE', 'None', 0)
    """
        )

        db.execSQL(/* sql = */ """
        INSERT INTO games_table VALUES (38, 0, 'Resident Evil 8: Village', 
'https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/1196590/capsule_616x353.jpg?t=1776927117
'
,1625673863000,
'Experience survival horror like never before in the eighth major installment in the storied Resident Evil franchise - Resident Evil Village.

Set a few years after the horrifying events in the critically acclaimed Resident Evil 7 biohazard, the all-new storyline begins with Ethan Winters and his wife Mia living peacefully in a new location, free from their past nightmares. 

Just as they are building their new life together, tragedy befalls them once again.
',
'قم بتجربة لعبة رعب البقاء كما لم تشهدها من قبل في الجزء الرئيسي الثامن لسلسلة Resident Evil- Resident Evil Village.

بعد سنوات قليلة من الأحداث المرعبة في Resident Evil 7 Biohazard التي نالت استحسان النقاد، تبدأ القصة الجديدة كليًا بـ"إيثان وينترز" وزوجته "ميا" اللذين يعيشان بهدوء في مكان جديد، متحررين من كوابيسهما السابقة. تصيبهم المأساة مجددًا، بينما يبنيان حياتهما الجديدة معًا.
',
4.64, 4.5, 'Survival horror, Puzzle, Action','رعب, حركة','RE', 'None', 0)
    """
        )

        //TRS

        db.execSQL(
            """
        INSERT INTO games_table VALUES (12, 0, 'Tomb Raider Definitive Edition', 
'https://image.api.playstation.com/cdn/EP0082/CUSA00109_00/IrvjYUmMqx3flPwlrij5JPOw4Lci8Bmm.png
'
,1389169752000,
'The Definitive Edition of the critically acclaimed action-adventure has been rebuilt for next-gen consoles, featuring an obsessively detailed Lara and a stunningly lifelike world. Endure high-octane combat, customize weapons and gear, and overcome grueling environments to survive Lara’s first adventure.',
'تدور هذه اللعبة حول مغامرة حركية سينمائية تتحول فيها (لارا كروفت) من مجرد امرأة شابة بلا خبرة إلى امرأة قوية نجحت في البقاء على قيد الحياة، وقد تمت إعادة تصميم هذه اللعبة لتلائم وحدات ألعاب الجيل التالي ولتقديم (لارا) في عالم واقعي بشكل مذهل. ستجعل (لارا) تخوض قتالاً في عالم مثير، وسيكون بإمكانك تخصيص أسلحتها ومعداتها والتغلب على عالم مليء بالمصاعب كي تتمكن من البقاء على قيد الحياة في مغامرتها الأولى والكشف عن السر المميت الخاص بالجزيرة. يتضمن الإصدار Definitive Edition من لعبة مغامرة الحركة التي حازت على إعجاب النقاد محتوى إضافيًا، وهو يضم كافة المحتويات القابلة للتنزيل.
',
4.68, 4.5, 'Puzzle, Shooter', 'مغامرة, حركة','TR','None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (13, 0, 'Shadow of the Tomb Raider', 
'https://m.media-amazon.com/images/M/MV5BNTI1ZTM4YjgtZjY0My00YTE2LTlhMTUtMGJlY2UwNzY1NjZmXkEyXkFqcGc@._V1_.jpg
'
,1536740952000,
'In Shadow of the Tomb Raider Definitive Edition experience the final chapter of Lara’s origin as she is forged into the Tomb Raider she is destined to be. Combining the base game, all seven DLC challenge tombs, as well as all downloadable weapons, outfits, and skills, Shadow of the Tomb Raider Definitive Edition is the ultimate way to experience Lara’s defining moment.
',
'في إصدار Shadow of the Tomb Raider Definitive Edition، تعرَّف على الفصل الأخير من نشأة لارا بينما تصبح غازية القبور كما تقرر مصيرها منذ ولادتها. مع الجمع بين مقابر التحدي والأسلحة والأزياء والمهارات المتضمنة في السبعة محتويات القابلة للتنزيل جميعها في إصدار Shadow of Tomb Raider Definitive Edition، فإنه الطريقة المثلى للتعرف على اللحظة المصيرية في حياة لارا.',
4.44, 4.5, 'Puzzle, Shooter','حركة','TR,SH', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (14, 0, 'Rise of the Tomb Raider', 
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTjgZbIHwV5aSRJG-A_ZnpUMYWIzB0EMG0Z0NIsEfhVBnvvdblA5fpAdcrz0qUWePqsnARfTBCNZ4nmyGrdqKoE4QV2mKb4rRStVfO4vwOs&s=10',
1536740952000,
'In Rise of the Tomb Raider, Lara Croft becomes more than a survivor as she embarks on her first Tomb Raiding expedition to the most treacherous and remote regions of Siberia.

Rise of the Tomb Raider: 20 Year Celebration is PS4™ Pro Enhanced and offers three modes to customize your gaming experience: 4K Resolution for unprecedented fidelity, High Framerate for even smoother gameplay, or Enriched Visuals for the lushest and most realistic graphics possible.

The game includes the new “Blood Ties” single player content, PSVR support for “Blood Ties”, new "Lara"s Nightmare" zombie combat mode, new online Co-op play for Endurance mode, new “Extreme Survivor” difficulty setting for the main campaign, 20 Year Celebration outfit and gun, and 5 classic Lara skins. It also includes all previously released downloadable content. With over 50 hours of gameplay, and nominated for more than 85 awards, this is the most comprehensive version of the critically-acclaimed experience.',
'Rise of the Tomb Raider: احتفالاً بمرور 20 سنة هي لعبة محسّنة على PS4™ Pro وتقدم ثلاثة أوضاع لتخصيص تجربة اللعبة: دقة عرض 4K لوضوح هائل، معدل إطارات مرتفع، أو بصريات ثرية للحصول على أفضل رسومات خصبة وواقعية.

تتضمن اللعبة محتوى اللعب الفردي Blood Ties، دعم PSVR لمحتوى Blood Ties، وضع Endurance للعب التعاوني غلى الإنترنت الجديد، مستوى الصعوبة Extreme Survivor للحملة الرئيسية، زي الاحتفال بمرور 20 سنة وسلاح، و5 من مظاهر "لارا". يتضمن أيضًا كل المحتوى القابل للتنزيل الذي تم إصداره سابقًا.تضمن أيضًا كل المحتوى القابل
',
4.5, 4.5, 'Puzzle, Shooter,Stealth','ألعاب تمثيل الأدوار, حركة' ,'TR','None', 0)
    """
        )


//


        db.execSQL(
            """
        INSERT INTO games_table VALUES (15, 0, 'The Outlast Trials', 
'https://blog.monsternotebook.com.tr/wp-content/uploads/5.The-Outlast-Trials-Arkadaslarinizla-Gerilim-Dolu-Anlar-Yasamaya-Hazir-Olun.jpg
',1683966552000,
'Red Barrels invites you to experience mind-numbing terror,
 this time with friends. Whether you go through the trials alone or in teams, if you survive long enough and complete the therapy, Murkoff will happily let you leave… but will you be the same?',
'تدعوك شركة Red Barrels لتجربة رعب يصيب العقل بالذهول، وهذه المرة مع الأصدقاء. سواء خضت الاختبارات بمفردك أو في فرق، إذا نجوت لفترة كافية وأكملت العلاج، فستسمح لك شركة Murkoff بالمغادرة بكل سرور... ولكن هل ستعود كما كنت؟
',
4.48, 4.5, 'Survival horror, Multiplayer ,Indie','رعب بقاء, لاعبين متعددين','H,P,M', 'None', 0)
    """
        )


        db.execSQL(
            """
        INSERT INTO games_table VALUES (16, 0, 'Outlast I', 
'https://image.api.playstation.com/cdn/UP2113/CUSA00325_00/M7Xcn9Q7mUkmm3dSilcsbkORlPPiQ0VK.png
',1378290389000,
'In the remote mountains of Colorado, horrors wait inside Mount Massive Asylum. A long-abandoned home for the mentally ill, recently re-opened by the “research and charity” branch of the transnational Murkoff Corporation, the asylum has been operating in strict secrecy… until now.

Acting on a tip from an anonymous source, independent journalist Miles Upshur breaks into the facility, and what he discovers walks a terrifying line between science and religion, nature and something else entirely. Once inside, his only hope of escape lies with the terrible truth at the heart of Mount Massive.

Outlast is a true survival horror experience which aims to show that the most terrifying monsters of all come from the human mind.',
 'في جبال كولورادو النائية، ينتظرك الرعب داخل مصحة "ماونت ماسيف". المصحة التي هُجرت طويلاً للمرضى النفسيين، أعيد فتحها مؤخراً بواسطة فرع "الأبحاث والجمعيات الخيرية"لشركة "موركوف" العابرة للقارات، حيث كانت تعمل في سرية تامة... حتى الآن.

بناءً على نصيحة من مصدر مجهول، يقتحم الصحفي المستقل "مايلز أبشور" المنشأة، وما يكتشفه هناك يسير على خط مرعب بين العلم والدين، والطبيعة وشيء آخر تماماً. وبمجرد دخوله، يصبح أمله الوحيد في الهروب هو كشف الحقيقة الرهيبة القابعة في قلب "ماونت ماسيف".

تقدم Outlast تجربة رعب بقاء حقيقية تهدف إلى إظهار أن أكثر الوحوش رعباً على الإطلاق هي التي تنبع من عقل الإنسان.

',
4.6, 5.0, ' Horror, Indie','رعب, مستقلة', 'H','None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (17, 0, 'Outlast II', 
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkIu8SKmaiAF-PKEqR3pFnf18X_8t0y87NBA&s'

,1493029589000,
'You are Blake Langermann, a cameraman working with your wife, Lynn. The two of you are investigative journalists willing to take risks and dig deep to uncover the stories no one else will dare touch.

You are following a trail of clues that started with the seemingly impossible murder of a pregnant woman known only as Jane Doe.

The investigation has lead you miles into the Arizona desert, to a darkness so deep that no one could shed light upon it, and a corruption so profound that going mad may be the only sane thing to do.
',
'أنت "بليك لانغرمان"، مصور فيديو يعمل مع زوجته "لين". كلاهما صحفيان استقصائيان مستعدان للمخاطرة والحفر عميقاً لكشف القصص التي لا يجرؤ أحد على لمسها.

أنتما تتبعان أثراً من الأدلة التي بدأت بجريمة قتل يبدو أنها مستحيلة لامرأة حامل تُعرف فقط باسم "جين دو".

لقد قادكما التحقيق لأميال داخل صحراء أريزونا، إلى ظلام دامس لدرجة أنه لا يمكن لأحد أن يسلط الضوء عليه، وإلى فساد عميق لدرجة أن الجنون قد يكون هو الشيء العاقل الوحيد الذي يمكنك القيام به.',
4.4, 4.5, ' Horror, Indie','رعب, مستقلة','H' ,'None', 0)
    """
        )

//
        db.execSQL(
            """
        INSERT INTO games_table VALUES (18, 0, 'Prey', 
'https://images.gog-statics.com/ed7a5f0cba0e3ee80bc5dd10683f6ce6fe692bb2b1764c4987aa97fbaaf142d1.jpg
',1493288789000,
'In Prey, you awaken aboard Talos I, a space station orbiting the moon in the year 2032. 

You are the key subject of an experiment meant to alter humanity forever – but things have gone terribly wrong. The space station has been overrun by hostile aliens and you are now being hunted. As you dig into the dark secrets of Talos I and your own past, you must survive using the tools found on the station, your wits, weapons, and mind-bending abilities.
',
'في Prey، تستيقظ خارج Talos I، سفينة الفضاء التي تدور حول القمر في 2032. وتكون أنت الحالة الرئيسية الخاضعة لإحدى التجارب التي تهدف إلى تغيير مسار البشرية إلى الأبد - – ولكن لا تسير الأمور على ما يرام. فقد اجتاح الغرباء العدوانيون سفينة الفضاء ويلاحقونك الآن. وببحثك في الأسرار الغامضة لسفينة الفضاء Talos I وماضيك، يتعين عليك النجاة مستخدمًا الأدوات التي تعثر عليها في سفينة الفضاء، وأفكارك، وأسلحتك، وقدرات العقلية الخاصة.',
4.5, 4.5, 'Immersive sim, Adventure','مطلق النار, مغامرة, حركة','SL', 'None', 0)
    """
        )

        //co-op
        db.execSQL(
            """
        INSERT INTO games_table VALUES (19, 0, 'Reanimal', 
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkLhkE9gnPjHBl8mPU8mk1pRhbgj840lGVjA&s'

,1770978389000,
'Tarsier Studios (Little Nightmares, Little Nightmares II) bring you a new and striking co-op horror adventure game.

You play as a brother and sister who must go through hell to rescue their missing friends and flee from a place they once called home.

Exploring by boat and on land, you must use your wits to survive, working together to escape the hellish island and the dark secret that haunts you

',
'ما REANIMAL؟
لقد عاد المبدعون الأصليون للعبتي Little Nightmares™ وLittle Nightmares™ II ليأخذوكم في رحلة أكثر رعبًا من أي وقت مضى. في لعبة المغامرة والرعب التعاونية هذه، تلعبون دور أخ وأخت يذهبان عبر الجحيم لإنقاذ أصدقائهما المفقودين. من خلال الاستكشاف بالقارب وعلى الأرض، يجب عليكم استخدام ذكائكم للبقاء على قيد الحياة، والعمل معًا للهروب من الجزيرة الجهنمية والسر المظلم الذي يطاردكم.

رعب وأمل
تركز هذه القصة المحيِّرة بأحداثها على مشاعر التوتر والأجواء المضطربة، حيث تنضمون إلى اليتيمين في بحثهما اليائس عن الأمل والخلاص في أشد الظروف صعوبة.

عبر عالم مظلم ومعقد
تجوّلوا في عالم مرعب ومثير للاهتمام، حيث لا يشكل المسار الرئيسي سوى جزء واحد من القصة المجزأة. اكتشفوا جميع أنواع المواقع الغامضة في رحلتكم المحفوفة بالمخاطر، ولكل منها قصتها الخاصة.

مغامرة مليئة بالرعب
تستخدم شركةTarsier Studios أسلوبها البصري الفريد في تصميم مجموعة كاملة من الوحوش الجديدة الشريرة وشخصيات الأطفال المحطمة والمقاوِمة في الوقت ذاته. وقد استُخدمت أجزاء من ماضي الأطفال المضطرب كمصدر إلهام لتصميم شخصياتهم والوحوش التي تعذبهم الآن.

تشاركوا الرعب
لا ينبغي إجبار أحد على التجول في الجحيم بمفرده! يمكن لعب REANIMAL كاملة في وضع لاعب فردي وفي وضع اللعب التعاوني المحلي وعبر الإنترنت، كما أنها تحتوي على كاميرا مشتركة موجهة مصممة لزيادة الشعور بالخوف من الأماكن المغلقة والتوتر.

',
4.32, 4.5, 'Co-op ,Adventure, Puzzle', 'رعب','M,H','None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (20, 0, 'A Way Out', 
'https://i.ytimg.com/vi/K_16pHHF0q8/maxresdefault.jpg
',1521800789000,
'From the creators of Brothers – A Tale of Two Sons comes A Way Out, an exclusively co-op adventure where you play the role of one of two prisoners making their daring escape from prison.

What begins as a thrilling breakout quickly turns into an unpredictable, emotional adventure unlike anything seen or played before.

A Way Out is an experience that must be played with two players. Each player controls one of the main characters, Leo and Vincent, in a reluctant alliance to break out of prison and gain their freedom.

Play the entire experience with your friends for free using the friends pass free trial feature. When you purchase the full game, you can invite any of your friends online regardless of whether or not they’ve purchased the game. From the in-game menu, send them an invite, they’ll unlock the Free Trial, and then you’re ready to play the entire experience together.
',
'إليك الترجمة العربية للعبة A Way Out، مصاغة بأسلوب المتجر الرسمي لتناسب قائمة الألعاب التعاونية في تطبيقك:

الترجمة العربية:
"من مبدعي Brothers – A Tale of Two Sons تأتي لعبة A Way Out، وهي مغامرة تعاونية حصرية تلعب فيها دور واحد من سجينين يقومان بهروب جريء من السجن.

ما يبدأ كعملية اختراق مثيرة يتحول سريعاً إلى مغامرة عاطفية غير متوقعة لا تشبه أي شيء شوهد أو لعب من قبل.

إن A Way Out هي تجربة يجب أن تُلعب مع لاعبين اثنين. يتحكم كل لاعب في إحدى الشخصيتين الرئيسيتين، "ليو" و"فينسنت"، في تحالف اضطراري لكسر قيود السجن ونيل حريتهما.

العب التجربة كاملة مع أصدقائك مجانًا باستخدام ميزة Friend"s Pass. عند شرائك للعبة الكاملة، يمكنك دعوة أي من أصدقائك عبر الإنترنت بغض النظر عما إذا كانوا قد اشتروا اللعبة أم لا.
',
4.2, 4.5, 'Co-op, Sleath ,Indie,Adventure ','تعاوني, تسلل, مستقلة, مغامرة','M', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (21, 0, 'It Takes Two', 
'https://www.journaldugeek.com/app/uploads/2022/04/sans-titre-8.jpg'

,1616667989000,
'Embark on the craziest journey of your life in It Takes Two,
 a genre-bending platform adventure created purely for co-op. Invite a friend to join for free with Friend’s Pass** and work together across a huge variety of gleefully disruptive gameplay challenges. Play as the clashing couple Cody and May, two humans turned into dolls by a magic spell.

Together, trapped in a fantastical world where the unpredictable hides around every corner, they are reluctantly challenged with saving their fractured relationship.

Master unique and connected character abilities in every new level. Help each other across an abundance of unexpected obstacles and laugh-out-loud moments. Kick gangster squirrels’ furry tails, pilot a pair of underpants, DJ a buzzing night club, and bobsleigh through a magical snow globe. Embrace a heartfelt and hilarious story, where narrative and gameplay are interwoven in a uniquely metaphorical experience.

',
'انطلق في الرحلة الأكثر جنونًا في حياتك في لعبة المغامرة القائمة على المنصة It Takes Two التي تجمع بين العديد من أنواع اللعب والمصممة بصورة أساسية للعب التعاوني. قم بدعوة صديق للعب بالمجان باستخدام Friend’s Pass** وانطلقا معًا عبر مجموعة كبيرة من تحديات أسلوب اللعب المرح والثوري.

أتقن قدرات الشخصيات المميزة والمتصلة في كل مستوى جديد. ساعدا بعضكما البعض عبر العقبات غير المتوقعة واللحظات المضحكة للغاية. انغمر في القصة المرحة والعاطفية عن علاقة محطمة.

تم تطوير لعبة It Takes Two بواسطة استوديو Hazelight الرائد في مجال اللعب التعاوني.

الميزات الرئيسية:
لعب تعاوني مثالي – قم بدعوة صديق للعب بالمجان باستخدام Friend’s Pass**.

أسلوب لعب مرح وثوري – بداية من المكانس الكهربائية الجامحة إلى معلّمي الحب الدمثين، مع لعبة It Takes Two لن تعرف أبدًا ما ستواجهه بعد ذلك.

قصة شاملة عن العلاقات – اكتشفا قصة مؤثرة وعاطفية ستقدرانها معًا!
',
4.54, 5.0, 'Co-op ,Adventure ', 'فريدة','M,B','None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (22, 0, 'Split Fiction', 
'https://store-images.s-microsoft.com/image/apps.61319.14618682156446874.2f5c3bab-6522-45a8-931d-deed3390944f.36b134dd-62c8-4e1a-af78-87357ddfbdb5
',1741256789000,
'From the inventive minds behind the award-winning It Takes Two comes an original and completely unpredictable new co-op action adventure.

In Split Fiction, you and your co-op partner will become Mio and Zoe, two writers trapped in a simulation of their own imaginations after a high-tech attempt to steal their creative ideas goes badly wrong. This pair of strangers will need to learn to work together in order to escape with their memories and stories intact. Prepare to deal with dragons and trolls in Zoe"s fantasy worlds and cyber ninjas and robo-parking attendants in Mio"s sci-fi creations. 

Together, you will need to overcome any obstacle thrown your way to make it through this weird, wild and wholly unexpected tale of friendship.
',
'انطلق في لحظات مذهلة بينما تعيش تجربة العديد من العوالم في Split Fiction، لعبة المغامرة التعاونية التي تتجاوز الحدود من الاستوديو الذي قدّم لعبة It Takes Two.‏ Mio وZoe هما كاتبتان متناقضتان تعلقان في قصصهما بعد أن تتصلا بآلة مصمّمة لسرقة أفكارهما الإبداعية. سيتعين عليهما الاعتماد بعضهما على بعض للتحرّر، والعمل معاً لإتقان مجموعة متنوّعة من القدرات والتغلّب على التحديات المتعدّدة مع الانتقال بين عوالم من الخيال العلمي والأوهام في هذه القصة غير المتوقّعة عن الصداقة.

• مغامرة تعاونية حقيقية – تم تصميم هذه المغامرة التي تُلعب على شاشة منقسمة للعبها بواسطة شخصين. عش تجربة أسلوب اللعب التعاوني حيث سيتعيّن عليكما تنسيق أفعالكما وتوقيتكما وكذلك العمل معاً للتغلّب على التحديات. قم بدعوة شريك للانضمام إليك بالمجان للعب عبر المنصات باستخدام تصريح الصديق*.

• تنوّع لا نظير له – اكتشف آليات وقدرات جديدة في كل مستوى من مغامرتك، والتي ستتبدّل بين بيئات الخيال العلمي والأوهام. اهرب من الشمس التي تتحوّل إلى نجم منفجر، وتحدّ أحد القرود في مواجهة من الرقص، وجرّب بعض الحيل الرائعة على لوح التزلج، وقاتل قطة شريرة وغير ذلك الكثير. عش تجربة عوالم مختلفة تماماً بعضها عن بعض، وقدرات جديدة غير متوقعة، ومجموعة من أساليب اللعب التي تتضمن المنصات، والتخفي، والأحجيات، وغير ذلك الكثير.',
4.6, 4.4, 'Co-op ,Adventure, Puzzle ', 'مغامرة','B','None', 0)
    """
        )

        //

        db.execSQL(
            """
        INSERT INTO games_table VALUES (23, 0, 'Detroit: Become Human', 
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbvHQhTTACjn8baC62umH5ee3czEqz1hdVXA&s'
,1527243989000,
'HOW FAR WILL YOU GO TO BE FREE?

Detroit, 2038. Lifelike androids have replaced the human workforce.

They never tire, never disobey and never say “no”… until something changes.
Some of them have started to behave irrationally, as if they were feeling emotions… 
Now you take control of three androids in their quest to discover who they really are.
Tell your own story and confront moral dilemmas and thought-provoking situations in a branching narrative where every choice you make has consequences.

Your actions and decisions shape the fate of the androids – and maybe even the future of the entire city.

',
'إلى أي مدى قد تغامر لتصير حرًا؟


شاهد لمحة من ثورة الأندرويد المستقبلية في هذا العرض التجريبي المفعم بالإثارة للعبة Detroit: Become Human.


نموذج الأندرويد الأولي الفريد "كونر" يعمل في دائرة شرطة "ديترويت"، وستنضم إليه في بداية مواجهة درامية مع أحد الهاربين.


عليك اختيار ما سيحدث بتحليل الموقف واتخاذ قراراتك بعناية... فهناك أرواح على المحك.


Detroit: Become Human هي لعبة إثارة شيقة تتبع أسلوب دراما neo-noir (الدراما السوداء الجديدة) حول أندرويد عاملين يخرجون عن برمجتهم ويطالبون بالحرية.',
4.73, 5.0, 'Decision Making, Sci-Fi ', 'مغامرة, حركة','SL','None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (24, 0, 'Silent Hill 2 REmake', 
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRViM4IjS_zlkbzbx_xOTkyVicY_W28MnCjqQ&s
',1759832789000,
'Experience a master class in psychological horror – lauded as the best in the series – on the latest hardware with chilling visuals and visceral sounds.

Take on the role of James Sunderland and venture into the mostly deserted town of Silent Hill in this highly anticipated remake of the 2001 classic. Drawn to this mysterious place by a letter from his wife, who died three years previously, James searches the town for any trace of his wife.

Enter a dream-like world and encounter twisted monsters, the menacing Pyramid Head, and a seemingly ordinary cast of characters wrestling with their past.

As James comes to terms with his own turmoil, he will be left with one question – why did he really come to Silent Hill?

',
'بعد أن تلقى رسالة من زوجته الميتة،
توجه جيمس إلى المكان الذي تشاركا فيه كثيرًا من الذكريات،
أملًا في رؤيتها مجددًا: سايلنت هيل.
هناك، بالقرب من البحيرة، وجد امرأةً تشبهها بشكل غريب...

"اسمي... ماريا"، ابتسمت المرأة. وجهها، وصوتها... إنها تشبهها تمامًا.

جرِّب رعب نجاة نفسيًّا من الطراز الأول -الذي تمت الإشادة به بأنه الأفضل في السلسلة- على أحدث الأجهزة مع مرئيات مرعبة وأصوات غريزية.

الصوتيات والرسومات فائقة الدقة

بفضل استخدام تقنية اقتفاء مسار الأشعة والتحسينات التقنية المتقدمة الأخرى، أصبح عالم SILENT HILL وأجواءه المربكة أكثر واقعيةً من أي وقت مضى.
وباعتماد مشاهد صوتية جديدة غامرة، ستشعر وكأنك في خضم المعمعة.

بيئات أشمل

استكشف المواقع والمباني التي كان يتعذر الوصول إليها من قبل، أو تمت إضافتها حديثًا في الإصدار الجديد.
خُض غمار نفس القصة المعروفة والشيقة، وتعرّف من جديد على مدينة Silent Hill المحدثة وخريطتها الموسعة.

كاميرا مثبتة على الكتف

بعد أن كانت تنقل الأحداث من منظور ثابت، أصبحت الكاميرا في الإصدار الجديد مثبتةً على كتف اللاعب وتنقل منظوره بشكل أكثر واقعيةً، حتى تتمكن من مشاهدة منظور James بشكل أكثر دقةً، وتحظى بمشاهد أكثر حماسيةً، وتجربةً أكثر متعةً أثناء اكتشاف المدينة ومواجهة الوحوش.
',
4.76, 4.5, 'Puzzle, Horror, Third-person shooter','رعب','H', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (25, 0, 'Silent Hill f', 
'https://www.konami.com/games_cms/promo/eu/uploads/d422e8eff58e04d9a473e581f15f1d4d-1024x576.jpg'
,1758685246000,
'In 1960s Japan, Shimizu Hinako"s secluded town of Ebisugaoka is consumed by a sudden fog, transforming her home into a haunting nightmare.

As the town falls silent and the fog thickens, Hinako must navigate the twisted paths of Ebisugaoka, solving complex puzzles and confronting grotesque monsters to survive.

Immerse yourself into Hinako"s world imagined by renowned author Ryukishi07. Experience entrancing music - including pieces by veteran Silent Hill composer Akira Yamaoka - and beautiful visuals in a gripping tale of doubt, regret and inescapable choices. Will Hinako embrace the beauty hidden within terror, or succumb to the madness that lies ahead?

Discover a new chapter in the Silent Hill series, blending psychological horror with a haunting Japanese setting.
',
'في اليابان في ستينيات القرن العشرين، اجتاح الضباب إيبيسوغاوكا المنعزلة، بلدة شيميزو هيناكو؛ مما حوّل موطنها إلى كابوس مخيف.

بينما يعم الصمت المدينة، ويتكاثف الضباب، ينبغي على هيناكو أن تسلك طرقات إبيسوغاوكا الملتوية، وتحل ألغازًا معقدة، وتواجه وحوشًا مخيفة من أجل النجاة.

اغمر نفسك في عالم هيناكو الذي تخيله المؤلف الشهير ريوكيشي07، مع موسيقى آسرة؛ بما في ذلك مقطوعات من تأليف أكيرا ياماوكا، ومرئيات جميلة في قصة مشوقة عن الشك، والندم، والاختيارات الحتمية. هل ستعانق هيناكو الجمال المخفي داخل الرعب، أم ستقع ضحية للجنون الذي ينتظرها؟

اكتشف فصلًا جديدًا في سلسلة SILENT HILL، يمزج بين الرعب النفسي وبيئة يابانية مسكونة.',
4.37, 4.5, ' Horror, Fighting, Adventure','رعب','H', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (26, 0, 'Red Dead Redemption 2', 
'https://m.media-amazon.com/images/I/71xeX+uUF1L.jpg
' ,1540438846000,
'America, 1899. The end of the wild west era has begun as lawmen hunt down the last remaining outlaw gangs. Those who will not surrender or succumb are killed.

After a robbery goes badly wrong in the western town of Blackwater, Arthur Morgan and the Van der Linde gang are forced to flee. With federal agents and the best bounty hunters in the nation massing on their heels, the gang must rob, steal and fight their way across the rugged heartland of America in order to survive.

As deepening internal divisions threaten to tear the gang apart, Arthur must make a choice between his own ideals and loyalty to the gang who raised him. 

From the creators of Grand Theft Auto V and Red Dead Redemption, Red Dead Redemption 2 is a sprawling tale of life in America at the dawn of the modern age.
',
'تدور الأحداث في أمريكا، في عام 1899. آرثر مورغان وعصابة فان دير ليند هاربون خارجون عن القانون، ولا بد أن تشق العصابة طريقها عبر قلب أمريكا الوعر بالسطو، والسرقة، والقتال من أجل النجاة، إذ يطاردهم عملاء فيدراليون وأفضل صائدي المكافآت في البلاد. ولأن الانقسامات الداخلية تترسخ وتهدد بتفرق العصابة، يجب على آرثر الاختيار بين مُثله العليا وولائه للعصابة التي ربَّته.

لعبة Red Dead Redemption 2 الحائزة أكثر من 175 جائزة في "جوائز لعبة العام" والتي تلقت أكثر من 250 نتيجة مثالية، عبارة عن قصة ملحمية عن الشرف والولاء في فجر العصر الحديث.

تتضمن Red Dead Redemption 2 أيضًا Red Dead Online — تجربة اللعب متعدد اللاعبين التي تدور أحداثها في عالم Red Dead Redemption 2 — العب بمفردك أو كوِّن فرقة، وهرِّب مشروب مونشاين، وقاتل رجال القانون، والعصابات، والحيوانات البرية الشرسة، وأكثر بينما تشق طريقك على الحدود الأمريكية.',
4.75, 4.5, 'Action-adventure, Open-world','فريدة, مغامرة, حركة', 'S,SL','None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (27, 0, 'Hades II', 
        'https://static.wikia.nocookie.net/hades_gamepedia_en/images/9/9f/H2_PackArt_600px.png/revision/latest?cb=20250925080214'
        ,
        1773446400000, 
        
'The first-ever sequel from Supergiant Games builds on the best aspects of the original god-like rogue-like dungeon crawler in an all-new, action-packed, endlessly replayable experience rooted in the Underworld of Greek myth and its deep connections to the dawn of witchcraft.

BATTLE BEYOND THE UNDERWORLD.

As the immortal Princess of the Underworld, you"ll explore a bigger, deeper mythic world, vanquishing the forces of the Titan of Time with the full might of Olympus behind you, in a sweeping story that continually unfolds through your every setback and accomplishment.
MASTER WITCHCRAFT AND DARK SORCERY. 
Infuse your legendary weapons of Night with ancient magick, so that none may stand in your way. Become stronger still with powerful Boons from more than a dozen Olympian gods, from Apollo to Zeus. There are nearly limitless ways to build your abilities. 
MINGLE WITH (MORE) GODS, GHOSTS, AND MONSTERS. 
Meet a cast of dozens of fully-voiced, larger-than-life characters, including plenty of new faces and some old friends. Grow closer to them through a variety of new interactions, and experience countless unique story events based on how your journey unfolds. 

EVERY RUN IS ITS OWN ADVENTURE.

New locations, challenges, upgrade systems, and surprises await as you delve into the ever-shifting Underworld again and again. Reveal the mysteries of the Altar of Ashes, tame witchy familiars, and gather reagents using Tools of the Unseen to get closer to your goal. 
THE PERKS OF IMMORTALITY. 
Thanks to a variety of permanent upgrades and the return of God Mode, you don"t have to be a god yourself to experience what Hades II has to offer. But if you happen to be one, you can brave escalating challenges for greater rewards, and prove just how divine you really are.', 
'يستند أول جزء ثانٍ على الإطلاق من Supergiant Games إلى أفضل جوانب لعبة الزحف في الزنازين من نوع روجلايك الأسطورية الأصلية، ليقدم تجربة جديدة تمامًا مفعمة بالحركة وقابلة لإعادة اللعب بلا حدود، تدور أحداثها في Underworld الأساطير اليونانية وصلاتها العميقة ببدايات فن السحر.

معركة تتجاوز حدود UNDERWORLD
تتقمص أنت دور أميرة Underworld الخالدة، إذ ستستكشف عالمًا أسطوريًا أوسع وأعمق، وستهزم قوى Titan of Time بكل ما أوتيت من قوة Olympus التي تدعمك، في قصة مذهلة تتكشف فصولها باستمرار مع كل إخفاق وإنجاز تحققه.

أتقن فن السحر والشعوذة المظلمة
عزّز أسلحة الليل الأسطورية بقوى سحرية قديمة، حتى لا يقف أحد في طريقك. اكتسب المزيد من القوى بفضل الهبات القوية المستمدة من العديد من الآلهة الأولمبية، من Apollo إلى Zeus. هناك طرق تكاد لا تُحصى لتطوير قدراتك.

تفاعل مع (المزيد من) الآلهة والأشباح والوحوش
التقِ بالعديد من الشخصيات الأسطورية ذات الأداء الصوتي الكامل، بما في ذلك العديد من الوجوه الجديدة وبعض الأصدقاء القدامى. وطّد صلتك بهم مستعينًا بمجموعة متنوعة من التفاعلات الجديدة، وعايش أجواء أحداث قصصية فريدة لا تُحصى تُنسج خيوطها حسب مسار رحلتك.

كل جولة تمثل مغامرة مستقلة
تنتظرك مواقع وتحديات وأنظمة تطوير ومفاجآت جديدة، بينما تغوص في Underworld دائم التغيّر مرةً تلو الأخرى. اكشف أسرار Altar of Ashes، وروّض الأرواح السحرية، واجمع المواد الكيميائية باستخدام أدوات Unseen لتقترب أكثر من هدفك.

مزايا الخلود
بفضل مجموعة متنوعة من الترقيات الدائمة وعودة God Mode، لن تحتاج لأن تكون كيانًا أسطوريًا لكي تجرب كل ما تقدّمه Hades II. لكن إن كنت كيانًا أسطوريًا، فيمكنك عندئذٍ خوض تحديات متصاعدة الصعوبة للحصول على مكافآت أكبر، وإثبات مدى القوة الأسطورية التي تتمتع بها.

أسلوب Supergiant المميّز
يُعدّ العرض البصري والسمعي الغني المفعم بالأجواء الساحرة، والسرد القصصي الممزوج بالحركة السريعة، السمة المميزة لألعاب Supergiant. بيئات جديدة حيوية مرسومة يدويًا، وشخصيات ثلاثية الأبعاد أكثر سلاسة في الوقت الفعلي، وموسيقى تصويرية أصلية مذهلة؛ كل ذلك يجعل هذا العالم الأسطوري يفيض بالحياة.',
4.7, 5.0, 'Dungeon crawl,Action,RPG', 'حركة','S','None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (28, 0, 'Ghost of Tsushima', 
'https://sm.ign.com/t/ign_pl/screenshot/default/6c19764beeb50db57a7a98bee6135a14_ja5g.1200.jpg
',1594957246000,
'   A storm is coming.
    Discover the expanded Ghost of Tsushima experience in the Director’s Cut.
Uncover the hidden wonders of Tsushima in this open-world action adventure from Sucker Punch Productions and PlayStation Studios, available for PS5 and PS4.

Forge a new path and wage an unconventional war for the freedom of Tsushima. Challenge opponents with your katana, master the bow to eliminate distant threats, develop stealth tactics to ambush enemies and explore a new story on Iki Island.
',
'هناك عاصفة قادمة. اكتشف تجربة لعب شبح تسوشيما الموسعة في إصدار المخرج.
استكشف عجائب تسوشيما المخفية في مغامرة قتالية في عالم مفتوح مُقدم لك من Sucker Punch Productions وPlayStation Studios. متاحة الآن على PS4 وPS5.

شق طريقًا جديدًا، وشن حربًا غير مألوفة من أجل حرية تسوشيما. تحدَ الأعداء بسيف الكاتانا، واحترف استعمال القوس للقضاء على التهديدات البعيدة، وطوّر أساليب تسلل لتنصب كمائنَ للأعداء، واستكشف قصة جزيرة إيكي الجديدة.

',
4.6, 4.5, 'Action, RPG , Fighting', 'ألعاب تمثيل الأدوار','S,SL,P','None', 0)
    """
        )


        db.execSQL(
            """
        INSERT INTO games_table VALUES (29, 0, 'Ghost of Yōtei', 
'https://static0.xdaimages.com/wordpress/wp-content/uploads/2025/10/ghostofyoteireview_featuredpic.jpg?w=1200&h=628&fit=crop
',1759378399000,
'    At the Northern edge of Japan, a new warrior rises

Set 300 years after the critically acclaimed Ghost of Tsushima, Ghost of Yōtei is a standalone experience set in 1600s rural Japan. The story follows a haunted, lone mercenary named Atsu. Thirsty for revenge, she travels through the beautiful, rugged landscapes of northern Japan, hunting those who killed her family many years earlier.

Sixteen years after her family’s death, Atsu"s quest across Ezo brings her to unexplored lands in search of a gang of six outlaws, but she finds much more than vengeance. 

Throughout her journey, Atsu will discover unlikely allies, and greater bonds than she could have imagined.
',
'ضع جانبًا جماعة فترة إيدو.

كن شبحًا مختلفًا واشهد قصة جديدة جريئة تقع أحداثها على خلفية مذهلة من التضاريس الوعرة في شمال اليابان.

تقع الأحداث بعد 300 عام من أحداث لعبة شبح تسوشيما التي أشاد بها النقاد، نتابع في هذه التجربة المستقلة جندية مرتزقة منفردة تطاردها الأشباح، تُدعى أتسو وتعيش في القرن السابع عشر. إنها متعطشة للانتقام، تنطلق في مطاردة الذين قتلوا عائلتها قبل عدة سنوات.

ستقودها رحلتها عبر إيزو إلى مناطق غير مستكشفة بينما تسعى للقضاء على عصابة من ستة مجرمين. وفي طريقها، ستواجه أمورًا أكثر بكثير مما توقعته في بداية رحلتها. ساعدها في العثور على حلفاء غير متوقعين وتكوين روابط دائمة وشق طريق نحو الشفاء والخلاص.

- استمتع بإثارة القتال، فالاستجابة اللمسية لوحدة التحكم اللاسلكية DualSense® تضع معارك قتال أتسو بسيف الكاتانا بين يديك.
- أتقن استخدام القوس والسهام أو خُطَّاف التسلق، واستشعر إثارة المعركة واستكشف أنواعًا مختلفة من التضاريس – كل هذا تعززه المُحفّزات التكيفية لوحدة التحكم اللاسلكية DualSense.
- استمتع بقوة جهاز PlayStation®5 مع أوقات تحميل بسرعة البرق، تنقلك سريعًا إلى البيئة الجميلة في شمال اليابان.
- استكشف بيئات خصبة نابضة بالحياة تجسدها رسومات مذهلة، بينما تتنقل أتسو عبر التلال الثلجية وحقول الزهور البرية والتضاريس الصخرية وغيرها.
- Tempest 3D AudioTech: انغمس في يابان القرن السابع عشر حيث يتيح التأثير الصوتي ثلاثي الأبعاد مشاهد صوتية محيطية مميزة¹.
- شبح يُوتِيه™ Legends هو وضع لعب جماعي تعاوني عبر الإنترنت² يدعم من لاعبين إلى أربعة، متاح الآن داخل اللعبة. هنا ستشكل مع أصدقائك فريقًا لتحدي اليوتيه الستة المتوحشين والخارقين للطبيعة. – مع أوضاع لعب متعددة داخل اللعبة ³

¹صوت ثلاثي الأبعاد عبر سماعات التلفزيون المدمجة أو سماعات رأس ستيريو عبر المدخل التقليدي أو USB. يلزم الإعداد.
² متاح عبر ملف التصحيح 1.5، يتطلب اتصالًا بالإنترنت وحساب PlayStation®. يلزم اشتراك PlayStation® Plus (يُباع على حدة) للعب عبر الإنترنت أو وضع اللاعبين المتعددين. يتضمن PS Plus رسوم اشتراك منتظمة تُخصم تلقائيًا حتى الإلغاء. تُطبَّق قيود السن. تُطبق الشروط: play.st/psplus-usageterms
³ التنين والسيد سايتو متوفران في تحديث مستقبلي (إصدار ملف تصحيح سيُعلن عنه لاحقًا).',
4.79, 4.5, 'Action, Fighting, Open-world','حركة', 'S,SL','None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (30, 0, 'The Uncertain: Last Quiet Day ', 
'https://cdn1.epicgames.com/2295b951b8f84e86b950bfaf7cadef60/offer/EGS_TheUncertainLastQuiteDay_NewGamesOrder_S2-1200x1600-df3b47639b2330ae9b0379c1ea583374.jpg
',1646281086000,
'  A Mind of Steel
Does the concept of morality still have a place in a world that is now ruled by pure mechanical logic, with every decision reduced to a simple set of ones and zeros? You’ll make tough choices through your adventure, and discover a whole new meaning of what morality has become.

  The Remains of a Biological Genius
Humanity has left behind a myriad of clues about the truth behind its disappearance. Not all of them will be easy to discover, as these secrets have been closely guarded. Test your skills and solve a diverse array of challenging puzzles; if you ever hope to uncover the hidden truth.

  See Earth Through Robotic Eyes
The ruins of civilization are filled with the air of mystery. In the Uncertain: Last Quiet Day, you’ll uncover a charming, yet lonely world like you’ve never seen before. What was once a loud and vibrant place is now reduced to an empty shell of its former glory, littered by the remains of what humanity once was.
',
'تحكم في الروبوت الهندسي RT-217NP بدافع من الفضول العجيب تجاه الجنس البشري المنقرض منذ أمد بعيد. استكشف أنقاض حضارة إنسانية كانت مزدهرة يومًا ما، وحاول اكتشاف حقيقة اختفاء البشرية.

عقلية لا تُقهر
هل لا يزال لمفهوم الأخلاق مكان في عالم يسوده الآن منطق الآلة البحت، مع تحويل كل قرار إلى بيانات إلكترونية غير ملموسة؟ ستتخذ خيارات صعبة خلال مغامرتك، وستكتشف معنى جديدًا تمامًا لما آلت إليه الأخلاق.

آثار نزعة بيولوجية
لقد خلفت الإنسانية وراءها عددًا لا يحصى من الأدلة حول حقيقة اختفائها. ولن يكون من السهل اكتشاف هذه الأسرار جميعًا؛ إذ تُفرض حماية شديدة عليها. اختبر مهاراتك وحل مجموعة متنوعة من الألغاز الصعبة إذا كنت ترغب في الكشف عن مكنون الحقيقة.

شاهد الأرض بأعين روبوت
تخيم أجواء من الغموض على أنقاض الحضارة. وفي لعبة The Uncertain: Last Quiet Day، ستكتشف عالمًا ساحرًا لكنه مهجور لم تره من قبل. فالمكان الذي كان ذات يوم صاخبًا وحيويًا، تحول الآن إلى مبنى فقد شموخه، وتناثرت فيه بقايا البشرية سابقًا.',
3.4, 3.5, 'Adventure, Decision Making','مغامرة','SL' ,'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (31, 0, ' Gang Beasts ', 
'https://m.media-amazon.com/images/M/MV5BM2RhM2JhMTgtNzM1ZS00NjJkLThkMzAtMjgyYTMzZWFmM2NhXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg',
1409285886000,
'  Gang Beasts is a silly multiplayer party game with surly gelatinous characters, brutal slapstick fight sequences, and absurd hazardous environments, set in the mean streets of Beef City.

Customise your character and fight local and online enemies in the melee game mode or fight with friends against the gangs of Beef City in the gang game mode.

Gang Beasts is made by Boneloaf, a small independent game studio making a series of experimental multiplayer party games.
',
'لعبة Gang Beasts هي لعبة احتفالية متعددة اللاعبين ومضحكة بشخصيات هلامية مشاكسة، مع سلسلة من القتالات المسلية العنيفة، وبيئات غريبة وخطرة، وسط شوارع مدينة Beef City.

يُمكنك تخصيص شخصيتك ومقاتلة الأعداء داخل اللعبة أو على الإنترنت في وضع لعب الاشتباك، أو القتال بجانب أصدقائك ضد عصابات Beef City في وضع لعب العصابة.

جانج بيستس من صنع بونيلوف، وهي استوديو صغير مستقل لصناعة الألعاب يقوم بصنع سلسلة من ألعاب الحفلات متعددة اللاعبين التجريبية.',
4.75, 4.5, 'Multiplayer, Fighting', 'قتال, ألعاب جماعية, حركة','B','None', 0)
    """
        )


        db.execSQL(
            """
        INSERT INTO games_table VALUES (32, 0, ' The Evil Within', 
'https://oyster.ignimgs.com/mediawiki/apis.ign.com/the-evil-within/d/dd/Theevilwithin530.jpg
',1418790833000,
'Developed by Shinji Mikami and the talented team at Tango Gameworks, The Evil Within embodies the meaning of pure survival horror. Highly-crafted environments, horrifying anxiety, and an intricate story are combined to create an immersive world that will bring you to the height of tension. With limited resources at your disposal, you’ll fight for survival and experience profound fear in this perfect blend of horror and action.
',
'تجسد لعبة The Evil Within التي طورها Shinji Mikami وفريقه الموهوب في Tango Gameworks المعنى العميق للرعب الكامن. تجتمع البيئات المصممة بحرفية عالية ولحظات القلق المرعبة والقصة المليئة بالألغاز المحيرة معًا لتكون عالمًا عميقًا يصعد بمشاعرك نحو قمة الإثارة. ستحارب وحدك بقواك المحدودة من أجل البقاء، وستعيش في أجواء الخوف المظلمة في هذا المزيج الفريد من الرعب والحركة. هذا الإصدار يدعم اللغة الإنجليزية.',
4.39, 3.5, 'Action, Horror','
رعب, حركة' ,'H','None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (33, 0, 'EA Sports FC 26', 
'https://i.ytimg.com/vi_webp/2a62c7jzhlY/maxresdefault.webp 
' , 1758858046000,
'   The club is yours in EA SPORTS FC 26.

Play your way with an overhauled gameplay experience powered by feedback from the FC Community, and choose between Authentic Gameplay and Competitive Gameplay presets.

Put your dream squad to the test in Football Ultimate Team, with Tournaments and Live Events, as well as a refreshed Rivals and Champs experience. 

Enjoy unrivalled authenticity in EA SPORTS FC 26, featuring 20,000+ players across 750+ clubs and national teams, in over 120+ stadiums and 35+ leagues. 
',
'النادي بين يديك في EA SPORTS FC™ 26.

العب بطريقتك مع تجربة لعب محدثة مدعومة بملاحظات المجتمع. يقدم خيار اللعب الواقعي الجديد تجربة كرة القدم الأكثر واقعية على الإطلاق في نمط المهنة، بينما تم تصميم خيار اللعب التنافسي - الذي يعتمد على أساسيات محسنة، وتناسق إضافي واستجابة محسنة - خصيصًا للعب في ™Football Ultimate Team ونمط الأندية.

اختبر فريق أحلامك في ™Football Ultimate Team مع أحداث مباشرة جديدة وأنماط البطولات، فضلاً عن تجربة Rivals و"الأبطال" محدثة.

اختبر مهنة المدير الفني كما لم يحدث من قبل مع تحديات المدير الفني المباشرة الجديدة تماماً. اربح المكافآت طوال الموسم الجديد عن طريق خوض مجموعة متنوعة من السيناريوهات الواقعية والقصص المتنوعة - بدءاً من بضع دقائق من اللعب إلى مواسم متعددة.

تقدم الأنماط النموذجية المستلهمة من عظماء اللعبة فئات جديدة إلى نمط الأندية ومهنة اللاعب، مما يضفي المزيد من التفرد على لاعبك. طوّر مهاراتك من خلال ترقية السمات وتحرير مزايا النمط النموذجي لتمنح لاعبك شعوراً خاصاً على أرض الملعب.

جرّب لعبة العالم كما لم تفعل من قبل في EA SPORTS FC™ 26، مع بيانات المباريات من الدوريات الكبرى في العالم التي تدعم أكثر من 20,000 لاعب حقيقي.',
3.4, 3.0, 'Sport, Multiplayer', 'رياضة','B,M','None', 0)
    """
        )


        //


        //upcoming games :

        db.execSQL(
            """
        INSERT INTO games_table VALUES (34, 1, ' Assassins Creed Black Flag Resynced ', 
'https://image.api.playstation.com/vulcan/ap/rnd/202603/1215/30972495c4d1b567dcd015b80c0d3af9c946efc8822944d7.jpg'
,1788755633000,
'    Raise the Black Flag and let your reign over the seas begin. 
The iconic solo pirate adventure returns, rebuilt for current hardware and powered by the latest Anvil engine. Sail the Caribbean as Edward Kenway during the Golden Age of Piracy in this faithfully enhanced remake of Assassin’s Creed IV: Black Flag, featuring stunning visuals, upgraded gameplay and new content.

Pave your way to greatness at the dawn of the 18th century during the Golden Age of Piracy. Sail and plunder the seas as an ally and friend to legends such as Blackbeard, Anne Bonny and Calico Jack. Uncover an ancient war separating Assassins and Templars, with the fate of everything the pirates have built hanging in the balance.
',
'جسّدوا قراصنة مهيبين
بثوا الخوف في قلوب أعدائكم بينما تصعدون على متن سفنهم وتُغرقونها مجسّدين شخصية إدوارد كينواي، قبطان جاكدو. سواء اندمجتم بين الحشود أم قدتم هجمات شرسة، يمكنكم التبديل بسلاسة بين عمليات الإطاحة الصامتة والاشتباكات المحتدمة بينما تستخدمون ببراعة السيوف والمسدسات والخنجر المخفي. تحدوا الإمبراطوريات وسط الصراع القديم بين الأساسنز وفرسان المعبد، بينما يدعمكم طاقم من أساطير القراصنة التاريخيين.

إعادة إصدار كلاسيكية لتجربة محسّنة
أُعيد تصميم القتال لتقديم مواجهات أكثر ديناميكية، مع التركيز على رد الضربات والإطاحة، بينما تم تحسين التخفي والباركور لتنفيذ عمليات الاغتيال والهروب بسلاسة أكبر. قوموا بترقية سفينتكم جاكدو باستمرار لمواجهة سفن الأعداء القوية والاستمتاع بآليات بحرية محسّنة تقدم أوضاع إطلاق نار بديلة جديدة. كما شملت تحسينات أسلوب اللعب معالجة المشاكل السابقة مما يضمن تحسين تجربتكم.

اختبروا الكاريبي بصورة لم تشهدوها من قبل
سواء أبحرتم في البحار المفتوحة أم انطلقتم في رحلات عبر الأراضي الوعرة، يمكنكم اكتشاف عالم مفتوح بسلاسة صُمم باستخدام أحدث محركات Anvil. استمتعوا بالآفاق الساحرة بينما تواجهون العواصف البحرية أو تغوصون بين حطام السفن أو تشقون طريقكم عبر الغابات المطيرة الكثيفة. بفضل تقنيات مثل Dolby Atmos وتتبع الأشعة، يبدو كل مشهد أكثر واقعية ليجعل جمال العالم ينبض بالحياة.

مغامرة إدوارد تتوسع
تقدم Assassin’s Creed Black Flag Resynced محتوى جديداً حصرياً يبني فوق قصة اللعبة الأصلية. ستعود وجوه مألوفة مع أحداث قصصية جديدة للشخصيات المفضلة لدى محبي اللعبة، مثل اللحية السوداء وستيد بونيت. كما ستتقاطع طرقكم مع طرق حلفاء غير متوقعين، حيث ينضم ثلاثة ضباط إليكم في رحلتكم كجزء من القصة الرئيسية. تنتظركم مزيد من المفاجآت مثل أناشيد بحّارة جديدة وحيوانات أليفة وطور التصوير وأكثر من ذلك.
',
0.0, 0.0, 'Adventure, Action ', 'مغامرة, حركة','SL','None', 0)
    """
        )


        db.execSQL(
            """
        INSERT INTO games_table VALUES (35, 1, ' 007 First Light ', 
'https://www.purepc.pl/image/news/2026/03/05_007_first_light_io_interactive_opowiada_o_podejsciu_do_nakreslenia_historii_jamesa_bonda_na_nowo_0_b.jpg
',1779770033000,
'After a heroic act, young Naval air crewman James Bond is offered to join the newly revived Double 0 program. But when a mission to stop a rogue agent ends in tragedy, he must join forces with his reluctant mentor Greenway to expose a deep conspiracy and stop a looming coup at the heart of the State.

  BECOME 007
Discover a new standalone, re-imagined James Bond origin story, and the events that lead an audacious young hero to become the best MI6 agent.

  A THRILLING ESPIONAGE ADVENTURE
Embark on missions in breathtaking locations, drive iconic vehicles, and dive into a cinematic adventure in pursuit of a rogue agent who’s always one step ahead.

  SPYING, YOUR WAY
Go silent or go loud. Whether fighting with fists or firepower, using gadgets to infiltrate, or bluffing your way past guards, the approach is entirely up to you.

  WELCOME TO MI6
Test your skills and replay your favorite missions with additional modifiers, for endless espionage fun!
',
'بعد عمل بطولي، يُمنح جيمس بوند الشاب في سلاح الجو البحري فرصة للانضمام إلى برنامج تدريب 00 الذي أُعيد إحياؤه. ولكن عندما تنتهي مهمة إيقاف عميل مارق بكارثة، تحتم عليه أن يتعاون مع معلمه المتحفظ "غرينواي" لكشف مؤامرة عميقة وإيقاف انقلاب وشيك في قلب الدولة.

كن العميل 007
اكتشف قصة بصياغة جديدة ومستقلة عن أصل جيمس بوند، والأحداث التي جعلت بطلاً شابًا جريئًا أفضل عميل في MI6.

مغامرة تجسس مثيرة
انطلق في مهام بمواقع خلابة، وقد سيارات أيقونية، وانغمر في مغامرة سينمائية بمطاردة عميل منشق دائمًا ما يكون متقدمًا بخطوة.

تجسس، بطريقتك
تحرّك بصمت أو اختر المواجهة الصاخبة. سواء قاتلت بالأيدي أو بالسلاح، أو تسللت بالأدوات أو خدعت الحراس... القرار لك.

مرحبًا بك في MI6
اختبر مهاراتك وأعد لعب مهامك المفضلة مع تعديلات إضافية، لتجربة تجسس لا تنتهي!',
0.0, 0.0, 'Adventure, Action ','
مغامرة, حركة', 'SL','None', 0)
    """
        )



        db.execSQL(
            """
        INSERT INTO games_table VALUES (36, 0, 'ARC Raiders', 
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIzm5DkLyiG2Nmyq2BRL6UMITequ-WyDwbcQ&s',
        1761812952000,
'From the creator of THE FINALS, ARC Raiders is an intense multiplayer extraction adventure in which squads and solo players compete against one another to scavenge valuable resources from the surface and safely extract. To do so, you will have to stay one step ahead of ARC"s terrifying machines and make it back in one piece.
Enlist as a Raider and shape your legacy as you scavenge the surface and build your home in the underground neighbourhood of Speranza. Beware of ARC’s deadly machines and the cut-throat Raiders who prey on others.
Build relationships with Speranza’s traders as you carry out quests and return from the surface with valuable loot. Sell it to traders or use it to craft gear and crucial upgrades back at your den. Nothing worth having comes free.
',
'ابحث عن الموارد وانجُ بحياتك وارتق بأمجادك
في لعبة ARC Raiders، تأخذك تجربة اللعب بين سطح الأرض الخاضع لهيمنة الآلات الفتاكة، ومجتمع Speranza الحيوي تحت الأرض. اصنع عتادك وأصلحه وطوّره في ورشتك الآمنة، قبل أن تجوب السطح للبحث عن الموارد في بقايا عالم مدمر، لكنه آسر الجمال. خض التجربة منفردًا أو ضمن فرق تصل إلى ثلاثة لاعبين، في مواجهة تهديدات آلات ARC المستمرة وقرارات الناجين الآخرين غير المتوقعة. وفي النهاية أنت وحدك من يقرر أي نوع من الـRaider ستكون - وإلى أي مدى ستقاتل لتنتصر.

استكشف عالمًا غامرًا
استكشف أربع خرائط فريدة عند الإطلاق، والمزيد مع تطور المجتمع تحت الأرض واتساع نطاقه. كل وجهة تحمل ثقل عالم دُمر مرتين وتُخفي في تضاريسها ندوب صراعات غابرة وأخرى ناشبة. ابحث بين الأنقاض عن الغنائم الثمينة، واجمع خيوط الماضي قبل أن تجتاح الطبيعة آثاره. تجعل الظروف المتغيرة للخرائط كل مغامرة مختلفة عن سابقتها، إذ يضفي تنوع الطقس والأعداء والآليات عنصرًا دائمًا من المفاجأة والخطر.

أثبت مكانتك
في مجتمع لا يعلو فيه سوى صوت الجرأة والشجاعة، عليك أن تثبت مكانتك كـRaider. يمكنك بيع ما تغتنمه من موارد مقابل العملات أو تحويله إلى عتاد جديد كليًا يمكنك من مواجهة المخاطر الفتاكة بكل جرأة. وبين انتصار وانكسار، تكتسب خبرة قيمة تفتح أمامك مهارات متنوعة وأساليب لعب جديدة. كما ستنجز مهامَّ لصالح التجار، لكلٍ دوافعه وخططه الخاصة، لتعايش مزيجًا من التوتر والتآزر داخل مجتمع يعيش على حافة الانهيار.

احذر الآلات
تهيمن الآلات الفتاكة المعروفة باسم ARC على سطح الأرض، من أسراب الطائرات الآلية التي لا ترحم إلى عمالقة ميكانيكيين يسحقون كل ما يعترض طريقهم. تبقى أصول هذه الآلات مجهولة لكن خطرها الدائم يحيط بك في كل خطوة تخطوها. لكل آلة قواها الخاصة وتكتيكاتها الفريدة، ما يحتم عليك كشف نقاط ضعفها والتصرف بسرعة. ولا تنسَ: صدى المعركة يمتد بعيدًا. فهناك Raiders آخرون يتربصون طامعين في غنيمتك.

ارسم مسارك
ينجو الـRaiders عبر إعادة تجميع الموارد المبعثرة، مستعينين بتقنيات قديمة وقطع منهوبة من ARC لصناعة الأسلحة والأدوات والعتاد. طور منصات ورشتك وتعلم المخططات لتصنيع عناصر أكثر تطورًا، أو ابتكر إصلاحات سريعة في أرض المعركة لتتفادى المآزق الطارئة. ومع ذيوع صيتك، ستتمكن من اختبار مهاراتك في مواجهة Raiders آخرين بخوض التجارب، لترتقي في قوائم المتصدرين وتحصد مكافآت قيمة.',
4.2, 4.5, 'Shooter , Multiplayer','حركة', 'M','None', 0)
    """
        )
        db.execSQL(
            """
        INSERT INTO games_table VALUES (40, 0, 'God of War Ragnarök', 
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_tsG3tuCl498BCSaHEU-gDwdLu1RV0KwL-A&s', 
        1667952000000,
'Fimbulwinter is well underway. Kratos and Atreus must journey to each of the Nine Realms in search of answers as Asgardian forces prepare for a prophesied battle that will end the world. 

Along the way they will explore stunning, mythical landscapes, and face fearsome enemies in the form of Norse gods and monsters. The threat of Ragnarök grows ever closer. 

Kratos and Atreus must choose between their own safety and the safety of the realms.',
        'شتاء فينبل على الأبواب. يجب على كريتوس وأتريوس الرحلة إلى كل من العوالم التسعة بحثًا عن إجابات بينما تستعد قوات أسغارد للمعركة المتنبأ بها التي ستنهي العالم. على طول الطريق سوف يستكشفون مناظر طبيعية أسطورية مذهلة، ويواجهون أعداء مخيفين من الآلهة والوحوش الإسكندنافية. يزداد تهديد راغناروك اقترابًا. 

يجب على كريتوس وأتريوس الاختيار بين سلامتهما وسلامة العوالم.',
        4.9, 4.8, 'Action, Adventure', 'أكشن, مغامرة', 'SL, P', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (41, 0, 'Bloodborne', 
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQk62h2PTD0r1rB9MFnK_eWPArp8FriPJp9cw&s', 
        1427155200000,
        'Face your fears as you search for answers in the ancient city of Yharnam, now cursed with a strange endemic illness spreading through the streets like wildfire.

Danger, death and madness lurk around every corner of this dark and horrific world, and you must discover its darkest secrets in order to survive. 

Armed with a unique arsenal of weaponry, including guns and saw cleavers, you will need wits, strategy and reflexes to take down the agile and intelligent enemies that guard the city.',
        'واجه مخاوفك بينما تبحث عن إجابات في مدينة يارنام القديمة، الملعونة الآن بمرض متوطن غريب ينتشر في الشوارع كالنار في الهشيم. 

الخطر والموت والجنون يتربصون في كل زاوية من هذا العالم المظلم والمروع، ويجب عليك اكتشاف أسراره الأكثر ظلاماً من أجل البقاء. مسلحاً بترسانة فريدة من الأسلحة، ستحتاج إلى الذكاء والاستراتيجية وسرعة البديهة للقضاء على الأعداء الأذكياء الذين يحرسون المدينة.',
        4.8, 4.7, 'Action RPG, Horror', 'تبادل أدوار, رعب', 'S', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (42, 0, 'Little Nightmares III', 
        'https://m.media-amazon.com/images/M/MV5BNzdhMTY1OTMtNzdjYi00YTJiLWEwNTctODMxMzYyOGFjZjZiXkEyXkFqcGc@._V1_.jpg', 
        1735689600000,
        'In Little Nightmares III, you follow the journey of Low & Alone, as they search for a path that could lead them out of the Nowhere. 

Trapped within the Spiral, a cluster of disturbing places, the two friends will have to work together to survive in a dangerous world full of delusions and escape the grasp of an even greater threat lurking in the shadows.
 
For the first time in the series, face your childhood fears together with a friend in online co-op, or solo with an AI companion.',
        'في Little Nightmares III، تتبع رحلة "لو" و"ألون"، حيث يبحثان عن طريق يمكن أن يقودهما خارج عالم "اللا مكان". 

بوقوعهما فريسة داخل "اللولب"، وهو مجموعة من الأماكن المزعجة، سيتعين على الصديقين العمل معًا للبقاء على قيد الحياة في عالم خطير مليء بالأوهام والهروب من قبضة تهديد أكبر يتربص في الظلال.
 
لأول مرة في السلسلة، واجه مخاوف طفولتك مع صديق عبر الإنترنت، أو بمفردك مع رفيق ذكاء اصطناعي.',
        3.81, 3.0, 'Adventure, Horror, Puzzle', 'مغامرة, رعب, الغاز', 'M, H', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (43, 0, 'The Last of Us Part I', 
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmvyJwI3iDZlBrmiBWNO7hWayFTnIZgTc3jA&s', 
        1662076800000,
        'In a ravaged civilization, where infected and hardened survivors run wild, Joel, a weary protagonist, is hired to smuggle 14-year-old Ellie out of a military quarantine zone. However, what starts as a small job soon transforms into a brutal cross-country journey.

Experience the emotional storytelling and unforgettable characters in The Last of Us, rebuilt for the PS5 console with enhanced visuals, new controls, and expanded accessibility options to feel more immersive than ever.',
        'في حضارة مدمرة، حيث يسرح المصابون والناجون القساة، يتم استئجار جويل لتهريب إيلي ذات الـ 14 عامًا من منطقة حجر صحي عسكرية. 

ومع ذلك، فإن ما يبدأ كمهمة صغيرة سرعان ما يتحول إلى رحلة وحشية عبر البلاد. استمتع بتجربة القصة العاطفية والشخصيات التي لا تُنسى في The Last of Us، والتي أُعيد بناؤها لجهاز PS5 مع مرئيات محسنة وعناصر تحكم جديدة وخيارات وصول موسعة لتشعر بالانغماس أكثر من أي وقت مضى.',
        4.9, 4.8, 'Action, Story-driven', 'أكشن, قصة', 'SL, P', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (44, 0, 'Cyberpunk 2077', 
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-ZVdZKk68fk6Zzk_WIWSlE-jyYpEbvVsXuA&s', 
        1607558400000,
        'Cyberpunk 2077 is an open-world, action-adventure RPG set in the megalopolis of Night City, where you play as a cyberpunk mercenary wrapped up in a fight for survival.

Customized with cybernetic enhancements, you can choose your own playstyle while taking on missions that impact the world around you. 

Now featuring the massive 2.0 update and Phantom Liberty expansion, dive into a world of high-tech espionage and high-stakes heists in a future that has been reimagined for the ultimate RPG experience.',
        'سايبر بانك 2077 هي لعبة أر بي جي ومغامرات في عالم مفتوح تقع أحداثها في مدينة "نايت سيتي"، حيث تلعب دور مرتزق يشارك في صراع من أجل البقاء. مجهزاً بتعزيزات سيبرانية، يمكنك اختيار أسلوب لعبك الخاص أثناء القيام بمهام تؤثر على العالم من حولك.

تتميز الآن بتحديث 2.0 الضخم وتوسعة Phantom Liberty، انغمس في عالم من التجسس عالي التقنية والسرقات عالية المخاطر في مستقبل أُعيد تصوره.',
        4.4, 4.5, 'RPG, Sci-Fi', 'تبادل أدوار, خيال علمي', 'SL, B', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (45, 0, 'Black Myth: Wukong', 
        'https://m.media-amazon.com/images/M/MV5BNGVmZTVjZDMtMzkyZi00MTczLWE4OTUtY2Y1ODBlMGFlYTAxXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg', 
        1724112000000,
        'Black Myth: Wukong is an action RPG rooted in Chinese mythology. 

The story is based on Journey to the West, one of the Four Great Classical Novels of Chinese literature. 

You shall set out as the Destined One to venture into the challenges and marvels ahead, to uncover the obscured truth beneath the veil of a glorious legend from the past. Master powerful staff combat and transform into different creatures to overcome the fierce and cunning foes that stand in your way.',
        'بلاك ميث: ووكونغ هي لعبة أكشن آر بي جي متجذرة في الأساطير الصينية.

تعتمد القصة على "رحلة إلى الغرب". 

ستنطلق بصفتك "المختار" لخوض التحديات والعجائب التي تنتظرك، لكشف الحقيقة المحجوبة تحت حجاب أسطورة مجيدة من الماضي. أتقن قتال العصا القوي وتحول إلى مخلوقات مختلفة للتغلب على الأعداء الشرسين والماكرين الذين يقفون في طريقك.',
        4.8, 4.9, 'Action RPG, Souls-like', 'أكشن, سولز', 'S, P', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (46, 0, 'Alan Wake 2', 
        'https://image.api.playstation.com/vulcan/ap/rnd/202305/2420/fbd0dcc88b31805fc7d49f59b8e0e5d0276403cde7fb8cc8.jpg', 
        1698364800000,
        'A string of ritualistic murders threatens Bright Falls, a small-town community enclosed by Pacific Northwest wilderness. 

Saga Anderson, an accomplished FBI agent, arrives to investigate. Meanwhile, Alan Wake, a lost writer trapped in a nightmare beyond our world, writes a dark story in an attempt to shape the reality around him and escape his prison.
 
Anderson and Wake are two heroes on two desperate journeys in two separate realities, connected in ways neither can understand.',
        'سلسلة من جرائم القتل الطقسية تهدد مدينة برايت فولز. تصل ساغا أندرسون، وهي عميلة في مكتب التحقيقات الفيدرالي، للتحقيق.

وفي الوقت نفسه، يكتب آلان ويك، وهو كاتب مفقود محاصر في كابوس خارج عالمنا، قصة مظلمة في محاولة لتشكيل الواقع من حوله والهروب من سجنه. أندرسون وويك بطلان في رحلتين يائستين في واقعين منفصلين، مرتبطان بطرق لا يستطيع أي منهما فهمها.',
        4.7, 4.6, 'Horror, Mystery', 'رعب, غموض', 'H, SL', 'None', 0)
    """
        )
        db.execSQL(
            """
        INSERT INTO games_table VALUES (47, 0, 'Elden Ring: Shadow of the Erdtree', 
'https://gaming-cdn.com/images/products/16007/orig/elden-ring-shadow-of-the-erdtree-edition-shadow-of-the-erdtree-edition-pc-game-steam-europe-cover.jpg?v=1718975409',
        1718928000000,
        'The Land of Shadow. 
A place obscured by the Erdtree. 
Where the goddess Marika first set foot. 

In these strange new lands, players discover the dark secrets of the world as they meet others who follow in Miquella’s footsteps with ulterior motives. Elden Ring: Shadow of the Erdtree features new weapons, equipment, weapon skills and magic not found in the base game, along with new enemies, boss encounters and plot lines to further increase players'' RPG freedom.',
        'أرض الظلال.
مكان يحجبه شجر الإردتري. 
حيث وطأت قدم الإلهة ماريكا لأول مرة. 
في هذه الأراضي الجديدة الغريبة، يكتشف اللاعبون الأسرار المظلمة للعالم بينما يقابلون آخرين يتبعون خطى ميكيلا. تتميز Shadow of the Erdtree بأسلحة ومعدات ومهارات وسحر جديد لم يتم العثور عليه في اللعبة الأساسية، إلى جانب أعداء وزعماء وقصص جديدة تزيد من حرية اللاعب.',
        4.9, 4.9, 'Action RPG, Souls-like', 'أكشن, سولز', 'S', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (48, 0, 'Lies of P', 
        'https://shopretrograde.com/cdn/shop/files/lies-of-p-pcvideo-gameretrograde-590552.jpg?v=1733631380', 
        1695081600000,
        'Lies of P is a thrilling souls-like that takes the story of Pinocchio, turns it on its head, and sets it against the darkly elegant backdrop of the Belle Époque era. 

You are a puppet created by Geppetto who is caught in a web of lies with unimaginable monsters and untrustworthy figures standing between you and the events that have befallen the world of Lies of P. 

You must always lie to others if you hope to become human. Discover what secrets the city of Krat holds.',
        'Lies of P هي لعبة سولز مثيرة تأخذ قصة بينوكيو وتقلبها رأسًا على عقب، وتضعها في خلفية أنيقة ومظلمة من عصر بيل إيبوك.

أنت دمية صنعها جيبيتو، عالق في شبكة من الأكاذيب مع وحوش لا يمكن تصورها وشخصيات لا توصف تقف بينك وبين الأحداث التي حلت بالعالم.
 
يجب عليك دائمًا أن تكذب على الآخرين إذا كنت تأمل في أن تصبح بشريًا. اكتشف الأسرار التي تخبئها مدينة كرات.',
        4.6, 4.5, 'Action RPG, Souls-like', 'أكشن, سولز', 'S, H', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (49, 0, 'Stray', 
        'https://m.media-amazon.com/images/I/61yylrfOeXL.jpg', 
        1658188800000,
        'Lost, alone and separated from family, a stray cat must untangle an ancient mystery to escape a long-forgotten cybercity and find its way home.

Stray is a third-person cat adventure game set amidst the detailed, neon-lit alleys of a decaying cybercity and the murky environments of its seedy underbelly. 

Roam surroundings high and low, defend against unforeseen threats and solve the mysteries of this unwelcoming place inhabited by curious droids and dangerous creatures.',
        'تائه ووحيد ومنفصل عن عائلته، يجب على قط ضال حل لغز قديم للهروب من مدينة إلكترونية منسية منذ زمن طويل والعثور على طريقه للمنزل.

Stray هي لعبة مغامرات قطط من منظور الشخص الثالث تقع أحداثها وسط أزقة نيون مفصلة لمدينة متحللة.
 
تجول في المحيط، ودافع ضد التهديدات غير المتوقعة وحل أسرار هذا المكان غير المضياف الذي يسكنه آليون فضوليون ومخلوقات خطيرة.',
        4.8, 4.5, 'Adventure, Indie', 'مغامرة, مستقلة', 'SL, B', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (50, 0, 'Monster Hunter Wilds', 
        'https://image.api.playstation.com/vulcan/ap/rnd/202409/0506/aa5c40ba185302dfcc88edc276a876fdc6c516c4db07ec9d.png', 
        1740614400000,
        'The unbridled force of nature runs wild.

Explore the Forbidden Lands, a place where the environment changes drastically from one moment to the next. This living world is inhabited by monsters that have adapted to these dynamic changes, including the mysterious Rey Dau.
In Monster Hunter Wilds, the hunt is more immersive than ever with seamless transitions from story to gameplay, and new features like the Seikret mount that allows for riding and weapon swapping on the fly.',
        'قوة الطبيعة الجامحة تنطلق. 

استكشف الأراضي المحظورة، حيث تتغير البيئة بشكل جذري من لحظة لأخرى. يسكن هذا العالم الحي وحوش تكيفت مع هذه التغييرات الديناميكية. في Monster Hunter Wilds،
 أصبح الصيد أكثر غماراً من أي وقت مضى مع انتقالات سلسة من القصة إلى اللعب، وميزات جديدة مثل مرافق الركوب الذي يسمح بالقتال وتبديل الأسلحة أثناء الحركة.',
        4.09, 3.0, 'Action, RPG', 'أكشن, تبادل أدوار', 'M, S', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (51, 0, 'The Day Before', 
        'https://m.media-amazon.com/images/M/MV5BZDAzZDFkOWUtYTJhMi00MTFjLWJhZGYtOWUyOGE2YWJjMDZiXkEyXkFqcGc@._V1_.jpg', 
        1701907200000,
        'The Day Before offered players a reimagined journey into a post-pandemic open-world MMO survival set in a present-day US east coast.

Scavenge through a deadly world, fight for survival against infected and other players, and build your own home in a society that has completely collapsed.
 
',
        'قدمت The Day Before للاعبين رحلة متخيلة في عالم مفتوح لنجاة جماعية بعد الوباء في الساحل الشرقي للولايات المتحدة. 

ابحث في عالم مميت، وقاتل من أجل البقاء ضد المصابين واللاعبين الآخرين، وابنِ منزلك الخاص في مجتمع انهار تمامًا.',
        1.2, 1.5, 'Survival, Open World', 'نجاة, عالم مفتوح', 'H, F', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (52, 0, 'Skull and Bones', 
        'https://image.api.playstation.com/vulcan/ap/rnd/202505/2720/c6ca32575a52c42b36b365c24cf59fa1ee0bca47ba4781ef.png', 
        1707782400000,
        'Enter the perilous paradise of Skull and Bones, inspired by the Indian Ocean during the Golden Age of Piracy, as you overcome the odds and rise from an outcast to an infamous pirate. 

Craft a variety of unique ships to survive, thrive, and rule in an immersive world that introduces new challenges and features every season. Be careful in this untamed open world, for there are predators lurking around every corner, from rival pirates to deadly sea creatures.',
        'ادخل إلى بارادايس الجمجمة والعظام المحفوفة بالمخاطر، المستوحاة من المحيط الهندي خلال العصر الذهبي للقرصنة، 

حيث تتغلب على الصعاب وتصعد من منبوذ إلى قرصان سيئ السمعة. اصنع مجموعة متنوعة من السفن الفريدة للبقاء والازدهار والسيطرة في عالم غامر يقدم تحديات وميزات جديدة في كل موسم. كن حذرًا في هذا العالم المفتوح غير المروض، فهناك مفترسون يتربصون في كل زاوية.',
        3.1, 2.9, 'Action, Naval Combat', 'أكشن, قتال بحري', 'S, M', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (53, 0, 'Forspoken', 
        'https://image.api.playstation.com/vulcan/ap/rnd/202203/0922/2gqcLQ5GUmznCHUZuuamxD06.png', 
        1674518400000,
        'Forspoken follows the journey of Frey, a young New Yorker transported to the beautiful and cruel land of Athia. 

In search of a way home, Frey must use her newfound magical abilities to traverse sprawling landscapes and battle monstrous creatures.
Frey soon learns this beautiful land once flourished under the reign of benevolent matriarchs, until a devastating blight relentlessly corrupted everything it touched.
 Frey reluctantly agrees to help the last remaining inhabitants.',
        'تتبع Forspoken رحلة "فري"، وهي شابة من نيويورك انتقلت إلى أرض "آثيا" الجميلة والقاسية.

بحثًا عن طريق للعودة، يجب على فري استخدام قدراتها السحرية المكتشفة حديثًا لعبور المناظر الطبيعية الشاسعة ومحاربة المخلوقات الوحشية. تدرك فري قريبًا أن هذه الأرض الجميلة ازدهرت ذات يوم، حتى أفسدت آفة مدمرة كل ما لمسته. 
توافق فري على مضض على مساعدة آخر السكان المتبقين.',
        3.4, 3.8, 'Action, RPG', 'أكشن, تبادل أدوار', 'SL, S', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (54, 0, 'Call of Duty: Vanguard', 
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2626uTGeBnGfBdbgcOS5uFWPugN9T9cR5-Q&s', 
        1636070400000,
        'Rise on every front: Dogfight over the Pacific, airdrop over France, defend Stalingrad with a sniper’s precision and blast through advancing forces in North Africa. 

The Call of Duty franchise returns with Call of Duty: Vanguard, where players will be immersed in visceral WWII combat on an unprecedented global scale. 

Experience the influential events of WWII through the eyes of a multinational group of heroes as they form Task Force One and change the face of history.',
        'انهض على كل جبهة: خض معارك جوية فوق المحيط الهادئ، واهبط بالمظلات فوق فرنسا،

ودافع عن ستالينغراد بدقة قناص، واخترق القوات المتقدمة في شمال إفريقيا. تعود سلسلة Call of Duty مع Vanguard،
 
حيث سيتعرف اللاعبون على قتال الحرب العالمية الثانية على نطاق عالمي غير مسبوق. اختبر الأحداث المؤثرة للحرب من خلال عيون مجموعة دولية من الأبطال وهم يشكلون "فرقة العمل الأولى".',
        3.5, 3.2, 'Shooter, Action', 'تصويب, أكشن', 'P', 'None', 0)
    """
        )
        db.execSQL(
            """
        INSERT INTO games_table VALUES (55, 0, 'The Witcher 3: Wild Hunt', 
        'https://i.pinimg.com/736x/5d/8a/41/5d8a41501af6aab5d2e754de44f58834.jpg', 
        1431993600000,
        'You are Geralt of Rivia, mercenary monster slayer.

Before you stands a war-torn, monster-infested continent you can explore at will. Your current contract? Tracking down Ciri — the Child of Prophecy, a living weapon that can alter the shape of the world.

Built for endless adventure, the massive open world of The Witcher sets new standards in terms of size, depth and complexity. 

Traverse a fantastical open world: explore forgotten ruins, caves and shipwrecks, and trade with merchants in cities.',
        'أنت جيرالت من ريفيا، صائد الوحوش المأجور.

أمامك قارة مزقتها الحرب وتغزوها الوحوش يمكنك استكشافها كما تشاء. 

عقدك الحالي؟ تعقب سيري - طفلة النبوءة، وهي سلاح حي يمكنه تغيير شكل العالم. وضع العالم المفتوح الضخم لـ ذا ويتشر معايير جديدة من حيث الحجم والعمق والتعقيد. 

اعبر عالماً مفتوحاً خيالياً: استكشف الأطلال المنسية والكهوف والسفن المحطمة وتاجر مع التجار في المدن.',
        4.9, 4.8, 'RPG, Open World', 'تبادل أدوار, عالم مفتوح', 'S, P', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (56, 0, 'EA Sports FC 24', 
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStCnkeuANSueTypiqqUelHjWbgDAZiyx2AsQ&s', 
        1695945600000,
        'EA SPORTS FC 24 marks the beginning of the future of football. 

Built on innovation and authenticity, feel closer to the game in the most true-to-football experience ever with HyperMotionV, PlayStyles optimized by Opta, and a revolutionized Frostbite Engine.
 
Develop club legends and improve your players with the brand new Ultimate Team Evolutions, and welcome women’s footballers to the pitch alongside men as you craft your dream XI. 

Experience unparalleled realism in every match.',
        'تمثل EA SPORTS FC 24 بداية مستقبل كرة القدم. 

اشعر بأنك أقرب إلى اللعبة مع التجربة الأكثر واقعية على الإطلاق بفضل تقنيات HyperMotionV ومحرك Frostbite الثوري.
 
قم بتطوير أساطير النادي وتحسين لاعبيك مع ميزة Ultimate Team Evolutions الجديدة كلياً، ورحب بكرة القدم النسائية في الملعب إلى جانب الرجال بينما تصمم تشكيلة أحلامك. اختبر واقعية لا مثيل لها في كل مباراة.',
        3.8, 3.5, 'Sports, Multiplayer', 'رياضة, لاعبين متعددين', 'M', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (57, 0, 'Assassin''s Creed Valhalla', 
        'https://myhotposters.com/cdn/shop/products/mL4873_1024x1024.jpg?v=1748533160', 
        1605571200000,
        'Become Eivor, a legendary Viking raider on a quest for glory. 

Explore England''s Dark Ages as you raid your enemies, grow your settlement, and build your political power. 

Lead epic Viking raids against Saxon troops and fortresses, and bring riches back to your people. 

Relive the visceral fighting style of a Viking warrior as you dual-wield powerful weapons and challenge yourself with the most varied collection of deadly enemies ever in Assassin''s Creed.',
        'كن إيفور، غازي الفايكنج الأسطوري في مهمة من أجل المجد.

استكشف العصور المظلمة في إنجلترا بينما تهاجم أعداءك، وتنمي مستوطنتك، وتبني قوتك السياسية.

قُد غارات الفايكنج الملحمية ضد القوات والحصون الساكسونية، 

واجلب الثروات لشعبك. استعد أسلوب القتال العنيف لمحارب الفايكنج بينما تستخدم أسلحة قوية مزدوجة وتتحدى نفسك مع مجموعة متنوعة من الأعداء.',
        4.3, 4.1, 'Action, RPG', 'أكشن, تبادل أدوار', 'SL, P', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (58, 0, 'Call of Duty: Warzone', 
        'https://i.ebayimg.com/images/g/pP8AAOSwQelkNZ7t/s-l1200.jpg', 
        1583808000000,
        'Welcome to Warzone, the massive free-to-play combat arena which now features Urzikstan, Rebirth Island, and Fortune’s Keep.

Explore a sprawling metropolitan area and engage in fast-paced, high-stakes combat. 

Drop in, armor up, loot for rewards, and battle your way to the top. With regular seasonal updates and new content added constantly, the battlefield is always changing, offering new strategies and experiences for players of all skill levels.',
        'مرحباً بكم في وارزون، ساحة القتال الضخمة والمجانية التي تضم الآن أورزيكستان وجزيرة ريبيرث وفورتشنز كيب. 

استكشف منطقة حضرية مترامية الأطراف وانخرط في قتال سريع الوتيرة. انزل، وتدرع، واجمع الغنائم، وقاتل في طريقك إلى القمة.
 
مع التحديثات الموسمية المنتظمة والمحتوى الجديد المضاف باستمرار، تتغير ساحة المعركة دائماً، وتقدم استراتيجيات وتجارب جديدة.',
        4.3, 4.0, 'Shooter, Battle Royale', 'تصويب, باتل رويال', 'M, P', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (59, 0, 'God of War', 
        'https://m.media-amazon.com/images/I/71rbhD4+MtL._AC_SL1000_.jpg', 
        1524182400000,
        'His vengeance against the Gods of Olympus years behind him, Kratos now lives as a man in the realm of Norse Gods and monsters. 

It is in this harsh, unforgiving world that he must fight to survive… and teach his son to do the same. 

This startling reimagining of God of War deconstructs the core elements that defined the series—satisfying combat, breathtaking scale, and a powerful narrative—and fuses them anew. 

Follow Kratos and Atreus on a deeply personal quest through the Nine Realms.',
        'بعد سنوات من انتقامه من آلهة أوليمبوس، يعيش كريتوس الآن كرجل في عالم الآلهة والوحوش الإسكندنافية.

في هذا العالم القاسي الذي لا يرحم، يجب عليه القتال من أجل البقاء... 

وتعليم ابنه أن يفعل الشيء نفسه. يعيد هذا التصور المذهل لـ God of War بناء العناصر الأساسية التي حددت السلسلة - القتال المرضي، والنطاق المذهل، والسرد القوي - ويدمجها من جديد في رحلة شخصية عميقة.',
        4.9, 4.9, 'Action, Adventure', 'أكشن, مغامرة', 'SL, P', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (60, 0, 'Uncharted: The Nathan Drake Collection', 
        'https://assets-prd.ignimgs.com/2022/01/04/unchartedcollection-1641338408063.jpg', 
        1444176000000,
        'Experience the journey that made Nathan Drake a legend. 

Follow the perilous travels of Drake across the globe, from humble beginnings to extraordinary discoveries.
 
Meet an unforgettable cast of characters as Drake puts life and friendship on the line in a race against ruthless enemies to uncover unimaginable treasure. 

This collection includes the single-player campaigns for Uncharted: Drake’s Fortune, Uncharted 2: Among Thieves, and Uncharted 3: Drake’s Deception, all rebuilt with improved visuals and performance.',
        'عش الرحلة التي جعلت ناثان دريك أسطورة.

اتبع رحلات دريك المحفوفة بالمخاطر عبر العالم، من البدايات المتواضعة إلى الاكتشافات غير العادية.
 
قابل مجموعة لا تُنسى من الشخصيات بينما يضع دريك حياته وصداقته على المحك في سباق ضد أعداء لا يرحمون للكشف عن كنوز لا يمكن تصورها.
 
تتضمن هذه المجموعة حملات اللاعب الفردي لـ Uncharted 1 و2 و3، والتي أُعيد بناؤها جميعاً بمرئيات وأداء محسّن.',
        4.8, 4.7, 'Action, Adventure', 'أكشن, مغامرة', 'U', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (61, 0, 'Uncharted 4: A Thief''s End', 
        'https://i0.wp.com/itsmoreofacomment.com/wp-content/uploads/2023/07/uncharted4.jpg?fit=1280%2C720&ssl=1', 
        1462838400000,
        'Several years after his last adventure, retired fortune hunter Nathan Drake is forced back into the world of thieves. 

With the stakes much more personal, Drake embarks on a globe-trotting journey in pursuit of a historical conspiracy behind a fabled pirate treasure.
 
His greatest adventure will test his physical limits, his resolve, and ultimately what he’s willing to sacrifice to save the ones he loves.
 
Explore incredibly detailed environments and experience the emotional conclusion to Nathan Drake''s story.',
        'بعد عدة سنوات من مغامرته الأخيرة،يُجبر صائد الكنوز المتقاعد ناثان دريك على العودة إلى عالم اللصوص. 

مع وجود دوافع شخصية أكبر بكثير، ينطلق دريك في رحلة حول العالم بحثاً عن مؤامرة تاريخية وراء كنز قرصان أسطوري.
 
ستختبر مغامرته الأكبر حدوده البدنية وعزيمته، وفي النهاية ما هو مستعد للتضحية به لإنقاذ من يحب. استكشف بيئات مفصلة بشكل مذهل وعش الخاتمة العاطفية لقصة ناثان دريك.',
        4.9, 4.8, 'Action, Adventure', 'أكشن, مغامرة', 'U, P', 'None', 0)
    """
        )


        db.execSQL(
            """
        INSERT INTO games_table VALUES (62, 0, 'Uncharted: The Lost Legacy', 
        'https://myhotposters.com/cdn/shop/products/mHP1714_1024x1024.jpg?v=1748538852', 
        1503360000000,
        'In order to recover an ancient artifact and keep it out of the hands of a ruthless warmonger, Chloe Frazer must enlist the aid of renowned mercenary Nadine Ross and venture to India’s Western Ghats to locate the Golden Tusk of Ganesh. 

In Chloe’s greatest journey yet, she must confront her past and decide what she’s willing to sacrifice to forge her own legacy. 

Experience a standalone adventure that features the series’ hallmark cinematic storytelling, exotic locations, and complex puzzles.',
        'من أجل استعادة قطعة أثرية قديمة وإبعادها عن أيدي صانع حرب لا يرحم، يجب على كلوي فريزر الاستعانة بمساعدة المرتزقة الشهيرة نادين روس والمغامرة في غاتس الغربية بالهند لتحديد موقع ناب غانيش الذهبي.

في أكبر رحلة لكلوي حتى الآن، يجب عليها مواجهة ماضيها وتحديد ما هي مستعدة للتضحية به لصنع إرثها الخاص. اختبر مغامرة مستقلة تتميز بسرد قصصي سينمائي وبيئات خلابة.',
        4.7, 4.5, 'Action, Adventure', 'أكشن, مغامرة', 'U', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (63, 0, 'Babylon''s Fall', 
'     https://blog.bestbuy.ca/wp-content/uploads/2022/05/Babylons-Fall-Review-Banner.jpg'  ,      1646265600000,
        'Experience the signature action combat from developer PlatinumGames in Babylon’s Fall. 

Join a group of warriors bonded with special equipment called Gideon Coffins in an odyssey to overcome the enormous Tower of Babylon. 

Master the art of wielding four weapons at once and customize your loadout to find the playstyle that suits you best. 

The world features a unique "oil painting" aesthetic.',
        'اختبر قتال الأكشن المميز من المطور PlatinumGames في Babylon’s Fall.

انضم إلى مجموعة من المحاربين المرتبطين بمعدات خاصة تسمى "توابيت جيديون" في رحلة للتغلب على برج بابل الضخم.
 
أتقن فن استخدام أربعة أسلحة في وقت واحد وخصص عتادك لتجد أسلوب اللعب الذي يناسبك بشكل أفضل في هذا العالم ذو الطابع الفني الفريد.',
        1.8, 2.0, 'Action, RPG', 'أكشن, تبادل أدوار', 'F , B', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (64, 0, 'Suicide Squad: Kill the Justice League', 
'https://i.redd.it/suicide-squad-kill-the-justice-league-promo-posters-v0-9sumwo7vvx5c1.jpg?width=2048&format=pjpg&auto=webp&s=627ee161afefe09329d4e90013eff305cd4b9e9e ',
       1706832000000, 
     'From the creators of Batman: Arkham, Suicide Squad: Kill the Justice League is a genre-defying third-person action shooter where the ultimate band of misfits must do the impossible to save the world: Kill the Justice League.

Join the newly "recruited" members of Amanda Waller’s infamous Task Force X as they set out on an impossible mission to kill the world''s greatest DC Super Heroes.',
        'من مبتكري سلسلة باتمان: أرخام، تأتي لعبة Suicide Squad: Kill the Justice League، وهي لعبة تصويب وأكشن من منظور الشخص الثالث حيث يجب على مجموعة من الخارجين عن القانون فعل المستحيل لإنقاذ العالم: قتل "فرقة العدالة". 

انضم إلى أعضاء فرقة "تاسك فورس إكس" في مهمة مستحيلة لقتل أعظم أبطال دي سي الخارقين.',
        2.5, 2.7, 'Action, Shooter', 'أكشن, تصويب', 'M, F', 'None', 0)
    """
        )

        db.execSQL(
            """
        INSERT INTO games_table VALUES (65, 1, 'Formula Team', 
'https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/1611840/header.jpg?t=1714028074',
       1890921667000, 
     ' Formula team management. The Formula Team will allow you to play the role of a team director, the success of the entire team will depend on you.

If you are a huge Formula fan and have always been excited about the racing atmosphere? This game is made just for you. That game that truly reflects the real thrill of the sport.

Thanks to this game you will be able to experience real racing emotions. You will be closer to these emotions than you can imagine. You will be responsible for planning, coordination, logistics, and even the color of the car, for literally every detail. You need passion, commitment and experience to be successful.
Formula Simulator offers a very realistic approach to the fastest world of motorsport.

Set up your own team, choose drivers, adapt everything to your own taste and participate in ruthless races. The speed and excitement involved at every corner of Formula Team sets it apart from all the other different types of racing games. The sound effects in the game combined with the high-quality graphics create a truly immersive racing experience.
',
 'إدارة فريق الفورمولا. ستتيح لك لعبة Formula Team لعب دور مدير الفريق، حيث سيعتمد نجاح الفريق بأكمله عليك.

إذا كنت من عشاق الفورمولا ومتحمساً دائماً لأجواء السباق، فهذه اللعبة صُنعت خصيصاً لك، فهي تعكس الإثارة الحقيقية للرياضة بشكل واقعي.

بفضل هذه اللعبة، ستتمكن من تجربة مشاعر السباق الحقيقية، وستكون أقرب إلى هذه المشاعر مما تتخيل. ستكون مسؤولاً عن التخطيط، التنسيق، اللوجستيات، وحتى لون السيارة، وكل التفاصيل حرفياً. أنت بحاجة إلى الشغف، الالتزام، والخبرة لتحقيق النجاح. تقدم لعبة Formula Simulator نهجاً واقعياً للغاية لعالم رياضة السيارات الأسرع.

أنشئ فريقك الخاص، اختر السائقين، قم بتخصيص كل شيء حسب ذوقك، وشارك في سباقات شرسة. السرعة والإثارة في كل منعطف تجعل Formula Team متميزة عن جميع ألعاب السباقات الأخرى. المؤثرات الصوتية في اللعبة المدمجة مع الرسومات عالية الجودة تخلق تجربة سباق غامرة حقاً.',

0.0, 0.0, 'Sports, Racing', 'رياضة , سباقات', 'B, M', 'None', 0)
    """
        )
        db.execSQL(
            """
        INSERT INTO games_table VALUES (66, 1, 'Phantom Blade Zero', 
'https://image.api.playstation.com/vulcan/ap/rnd/202512/1205/4946bed5501a4bd352f905bf4dc11bdda4de0871cf0255ab.jpg',     
  1788883267000, 
     ' S-GAME presents Phantom Blade Zero, a fusion of classic Wuxia storytelling and exhilarating action powered by Unreal Engine 5. 
You play as Soul, a warrior with only sixty-six days left to live.
Fend off assassins, unravel world-ending conspiracies, and defend your values with your life.
',
 'تقدم شركة S-GAME لعبة Phantom Blade Zero، وهي مزيج من قصص الـ Wuxia الكلاسيكية والأكشن المثير المدعوم بمحرك Unreal Engine 5.
تلعب بدور Soul، وهو محارب لم يتبقَ في عمره سوى ستة وستين يوماً فقط.
تصدَّ للمغتالين، واكشف المؤامرات التي تهدد بنهاية العالم، ودافع عن قيمك بحياتك.',

0.0, 0.0, 'Action, Fighting, RPG', 'اكشن, معارك, تبادل ادوار', 'SL,P', 'None', 0)
    """
        )
        db.execSQL(
            """
        INSERT INTO games_table VALUES (67, 1, 'Grand Theft Auto VI', 
'https://image.api.playstation.com/vulcan/ap/rnd/202505/0616/c9f078c260d79339cb581054ce5ca49f2b56ab943d1beb20.png', 
 1795104067000, 
     ' Vice City, USA.

Jason and Lucia have always known the deck is stacked against them. 
But when an easy score goes wrong, they find themselves on the darkest side of the sunniest place in America, in the middle of a criminal conspiracy stretching across the state of Leonida — forced to rely on each other more than ever if they want to make it out alive.
',
 'فايس سيتي، الولايات المتحدة الأمريكية.

لطالما علم جيسون ولوسيا أن الظروف تقف ضدهما. ولكن عندما تسير سرقة سهلة بشكل خاطئ، يجدان نفسهما في الجانب الأكثر ظلاماً من المكان الأكثر إشراقاً في أمريكا، في منتصف مؤامرة إجرامية تمتد عبر ولاية ليونيدا — ومجبرين على الاعتماد على بعضهما البعض أكثر من أي وقت مضى إذا أرادا النجاة والخروج بأمان.',

0.0, 0.0, 'Shooter, Action-adventure, Nonlinear gameplay', 'اكشن-مغامره, تصويب, عالم مفتوح', 'P', 'None', 0)
    """
        )

        /*
    db.execSQL("""
        INSERT INTO games_table VALUES (35, 1, ' 007 First Light ', 
''
,1779770033,
'


',
0.0, 0.0, 'Adventure, Action ', 'None', 0)
    """)

*/

        db.execSQL("""
    UPDATE games_table
    SET
    isUpcoming = 0,
    PSrating = 4.42,
    Steamrating = 3.00
     WHERE id = 4
""".trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS reviews_table")
        db.execSQL("DROP TABLE IF EXISTS games_table")
        onCreate(db)
    }

}