package com.translate.utils

import com.translate.data.local.HistoryDao
import com.translate.data.model.Language
import com.translate.data.networking.DictionaryClient
import com.translate.data.networking.TranslationClient
import com.translate.tts.SpeechToTextService
import com.translate.tts.TextToSpeechService
import org.jetbrains.compose.resources.DrawableResource
import translate.composeapp.generated.resources.Res
import translate.composeapp.generated.resources.abrahamlincoln
import translate.composeapp.generated.resources.afrikaans
import translate.composeapp.generated.resources.albanian
import translate.composeapp.generated.resources.alberteinstein
import translate.composeapp.generated.resources.amharic
import translate.composeapp.generated.resources.arabic
import translate.composeapp.generated.resources.armenia
import translate.composeapp.generated.resources.assamese
import translate.composeapp.generated.resources.aymara
import translate.composeapp.generated.resources.azerbaijani
import translate.composeapp.generated.resources.bambara
import translate.composeapp.generated.resources.basque
import translate.composeapp.generated.resources.belarusian
import translate.composeapp.generated.resources.bengali
import translate.composeapp.generated.resources.bhojpuri
import translate.composeapp.generated.resources.bosnia
import translate.composeapp.generated.resources.brucelee
import translate.composeapp.generated.resources.bulgaria
import translate.composeapp.generated.resources.catalan
import translate.composeapp.generated.resources.cebuano
import translate.composeapp.generated.resources.chichewa
import translate.composeapp.generated.resources.chinese
import translate.composeapp.generated.resources.corsican
import translate.composeapp.generated.resources.croatian
import translate.composeapp.generated.resources.czech
import translate.composeapp.generated.resources.dalailama
import translate.composeapp.generated.resources.danish
import translate.composeapp.generated.resources.dari_persian
import translate.composeapp.generated.resources.dhivehi
import translate.composeapp.generated.resources.dogri
import translate.composeapp.generated.resources.dutch
import translate.composeapp.generated.resources.eleanorroosevelt
import translate.composeapp.generated.resources.elonmusk
import translate.composeapp.generated.resources.english
import translate.composeapp.generated.resources.esperanto
import translate.composeapp.generated.resources.estonian
import translate.composeapp.generated.resources.ewe
import translate.composeapp.generated.resources.filipino
import translate.composeapp.generated.resources.finnish
import translate.composeapp.generated.resources.florencenightingale
import translate.composeapp.generated.resources.french
import translate.composeapp.generated.resources.frisian
import translate.composeapp.generated.resources.galician
import translate.composeapp.generated.resources.georgia
import translate.composeapp.generated.resources.german
import translate.composeapp.generated.resources.grayfarrah
import translate.composeapp.generated.resources.greek
import translate.composeapp.generated.resources.gujarati
import translate.composeapp.generated.resources.haitian
import translate.composeapp.generated.resources.hausa
import translate.composeapp.generated.resources.hawaiian
import translate.composeapp.generated.resources.hebrew
import translate.composeapp.generated.resources.henryford
import translate.composeapp.generated.resources.hindi
import translate.composeapp.generated.resources.hmong
import translate.composeapp.generated.resources.hungary
import translate.composeapp.generated.resources.ic_auto
import translate.composeapp.generated.resources.iceland
import translate.composeapp.generated.resources.igbo
import translate.composeapp.generated.resources.indonesian
import translate.composeapp.generated.resources.irish
import translate.composeapp.generated.resources.italian
import translate.composeapp.generated.resources.japanese
import translate.composeapp.generated.resources.javanese
import translate.composeapp.generated.resources.kannada
import translate.composeapp.generated.resources.kazakh
import translate.composeapp.generated.resources.khmer
import translate.composeapp.generated.resources.kinyarwanda
import translate.composeapp.generated.resources.konkani
import translate.composeapp.generated.resources.korean
import translate.composeapp.generated.resources.kusdish
import translate.composeapp.generated.resources.kyrgyz
import translate.composeapp.generated.resources.lao
import translate.composeapp.generated.resources.latin
import translate.composeapp.generated.resources.latvian
import translate.composeapp.generated.resources.levtolstoy
import translate.composeapp.generated.resources.lingala
import translate.composeapp.generated.resources.lithuanian
import translate.composeapp.generated.resources.luxembourgish
import translate.composeapp.generated.resources.mahatmaghandi
import translate.composeapp.generated.resources.maithili
import translate.composeapp.generated.resources.malagasy
import translate.composeapp.generated.resources.malay
import translate.composeapp.generated.resources.malayalam
import translate.composeapp.generated.resources.malijinnah
import translate.composeapp.generated.resources.maltese
import translate.composeapp.generated.resources.maori
import translate.composeapp.generated.resources.marathi
import translate.composeapp.generated.resources.marktwain
import translate.composeapp.generated.resources.mayaangelou
import translate.composeapp.generated.resources.michaelkingjr
import translate.composeapp.generated.resources.mongolian
import translate.composeapp.generated.resources.motherteresa
import translate.composeapp.generated.resources.myanmar
import translate.composeapp.generated.resources.nelsonmandela
import translate.composeapp.generated.resources.nepali
import translate.composeapp.generated.resources.northmacedonia
import translate.composeapp.generated.resources.norwegian
import translate.composeapp.generated.resources.nyanja
import translate.composeapp.generated.resources.odia
import translate.composeapp.generated.resources.oprahwinfrey
import translate.composeapp.generated.resources.oromo
import translate.composeapp.generated.resources.pablopicasso
import translate.composeapp.generated.resources.pashto
import translate.composeapp.generated.resources.paulwalker
import translate.composeapp.generated.resources.persian
import translate.composeapp.generated.resources.polish
import translate.composeapp.generated.resources.portuguese
import translate.composeapp.generated.resources.punjabi
import translate.composeapp.generated.resources.quechua
import translate.composeapp.generated.resources.romania
import translate.composeapp.generated.resources.russian
import translate.composeapp.generated.resources.samon
import translate.composeapp.generated.resources.sanskrit
import translate.composeapp.generated.resources.scots_gaelic
import translate.composeapp.generated.resources.sepedi
import translate.composeapp.generated.resources.serbian
import translate.composeapp.generated.resources.sesotho
import translate.composeapp.generated.resources.shona
import translate.composeapp.generated.resources.sindhi
import translate.composeapp.generated.resources.sinhala
import translate.composeapp.generated.resources.slovak
import translate.composeapp.generated.resources.slovenia
import translate.composeapp.generated.resources.somali
import translate.composeapp.generated.resources.spanish
import translate.composeapp.generated.resources.stevejobs
import translate.composeapp.generated.resources.sundanese
import translate.composeapp.generated.resources.swahili
import translate.composeapp.generated.resources.sweden
import translate.composeapp.generated.resources.tajik
import translate.composeapp.generated.resources.tamil
import translate.composeapp.generated.resources.tatar
import translate.composeapp.generated.resources.telugu
import translate.composeapp.generated.resources.thai
import translate.composeapp.generated.resources.tigrinya
import translate.composeapp.generated.resources.tsonga
import translate.composeapp.generated.resources.turkish
import translate.composeapp.generated.resources.turkmen
import translate.composeapp.generated.resources.ukrainian
import translate.composeapp.generated.resources.urdu
import translate.composeapp.generated.resources.uyghur
import translate.composeapp.generated.resources.uzbek
import translate.composeapp.generated.resources.vietnam
import translate.composeapp.generated.resources.vincentvangog
import translate.composeapp.generated.resources.waltdisney
import translate.composeapp.generated.resources.waltwhitman
import translate.composeapp.generated.resources.welsh
import translate.composeapp.generated.resources.williamshakespeare
import translate.composeapp.generated.resources.xhosa
import translate.composeapp.generated.resources.yiddish
import translate.composeapp.generated.resources.yoyuba
import translate.composeapp.generated.resources.zulu

const val SHARED_PREFERENCE_KEY = "translate"
const val ERROR_BANNER_SIZE_MISMATCH = 102
const val ERROR_EMPTY_BID_TOKEN = 104
const val ERROR_AD_ALREADY_REQUESTED = 105
const val ERROR_AD_FORMAT_UNSUPPORTED = 108
const val MODULE_VERSION = 117
const val ERROR_INVALID_SERVER_PARAMETERS = 110
const val ERROR_PRESENTATION_AD_NOT_READY = 106

object Constant {
    val languageList = arrayListOf(
        Language("ic_auto", "Detect Language", "Detect", "detect", true, 0),
        Language("afrikaans", "Afrikaans", "(Afrikaans)", "af", true, 1),
        Language("albanian", "Albanian", "(shqip)", "sq", true, 2),
        Language("amharic", "Amharic", "(ኣማርኛ)", "am", false, 3),
        Language("arabic", "Arabic", "(العربية)", "ar", true, 4),
        Language("armenia", "Armenian", "(հայերեն)", "hy", true, 5),
        Language("assamese", "Assamese", "(অসমীয়া)", "as", false, 6),
        Language("aymara", "Aymara", "(Aymar Aru)", "ay", false, 7),
        Language("azerbaijani", "Azerbaijani", "(Azərbaycan dili)", "az", false, 8),
        Language("bambara", "Bambara", "(Bamanankan)", "bm", false, 9),
        Language("basque", "Basque", "(euskara)", "eu", false, 10),
        Language("belarusian", "Belarusian", "(беларуская мова)", "be", false, 11),
        Language("bengali", "Bengali", "(বাংলা)", "bn", true, 12),
        Language("bhojpuri", "Bhojpuri", "(भोजपुरी)", "bho", false, 13),
        Language("bosnia", "Bosnian", "(bosanski)", "bs", true, 14),
        Language("bulgaria", "Bulgarian", "(български)", "bg", false, 15),
        Language("myanmar", "Burmese", " (မြန်မာစာ)", "my", true, 16),
        Language("catalan", "Catalan", "(català)", "ca", true, 17),
        Language("cebuano", "Cebuano", "(Sinugboanon)", "ceb", false, 18),
        Language("chichewa", "Chichewa", "(Chicheŵa)", "ny", false, 19),
        Language("chinese", "Chinese Simp", "(中文)", "zh-Hans", true, 20),
        Language("chinese", "Chinese Trad", "(中文)", "zh-TW", true, 21),
        Language("corsican", "Corsican", "(corsu)", "co", false, 22),
        Language("croatian", "Croatian", "(Hrvatski)", "hr", true, 23),
        Language("czech", "Czech", "(čeština)", "cs", true, 24),
        Language("danish", "Danish", "(dansk)", "da", true, 25),
        Language("dhivehi", "Dhivehi", "(ދިވެހި)", "dv", false, 26),
        Language("dogri", "Dogri", "(डोगरी)", "doi", false, 27),
        Language("dutch", "Dutch", "(Nederlands)", "nl", true, 28),
        Language("english", "English", "(English)", "en", true, 29),
        Language("esperanto", "Esperanto", "(Esperanto)", "eo", true, 30),
        Language("estonian", "Estonian", "(Eesti keel)", "et", true, 31),
        Language("ewe", "Ewe", "(Eʋegbe)", "ee", false, 32),
        Language("filipino", "Filipino", "(Philipino)", "tl", true, 33),
        Language("finnish", "Finnish", "(suomi)", "fi", true, 34),
        Language("french", "French", "(français)", "fr", true, 35),
        Language("frisian", "Frisian", "(Frysk)", "fy", false, 36),
        Language("galician", "Galician", "(Galego)", "gl", false, 37),
        Language("georgia", "Georgian", "(ქართული)", "ka", false, 38),
        Language("german", "German", "(Deutsch)", "de", true, 39),
        Language("greek", "Greek", "(ελληνικά)", "el", true, 40),
        Language("gujarati", "Gujarati", "(ગુજરાતી)", "gu", true, 41),
        Language("haitian", "Haitian Creole ", "(Kreyòl ayisyen)", "ht", false, 42),
        Language("hausa", "Hausa", "(Hausa)", "ha", false, 43),
        Language("hawaiian", "Hawaiian", "(ʻōlelo Hawaiʻi)", "ha", false, 44),
        Language("hebrew", "Hebrew", "(עברית)", "iw", false, 45),
        Language("hindi", "Hindi", " (हिन्दी)", "hi", true, 46),
        Language("hmong", "Hmong", "(Hmong)", "hmn", false, 47),
        Language("hungary", "Hungarian", "(magyar)", "hu", true, 48),
        Language("iceland", "Icelandic", "(Íslenska)", "is", true, 49),
        Language("igbo", "Igbo", "(Ṇ́dị́ Ìgbò)", "ig", false, 50),
        Language("indonesian", "Indonesian", "(Bahasa Indonesia)", "id", true, 51),
        Language("irish", "Irish", "(Gaeilge)", "ga", false, 52),
        Language("italian", "Italian", "(italiano)", "it", true, 53),
        Language("japanese", "Japanese", "(日本語)", "ja", true, 54),
        Language("javanese", "Javanese", "(basa Jawa)", "jv", true, 55),
        Language("kannada", "Kannada", "(ಕನ್ನಡ)", "kn", true, 56),
        Language("kazakh", "Kazakh", "(Қазақ тілі)", "kk", false, 57),
        Language("khmer", "Khmer", "(ខេមរភាសា)", "km", true, 58),
        Language("kinyarwanda", "Kinyarwanda", "(Ikinyarwanda)", "rw", false, 59),
        Language("konkani", "Konkani", "(कोंकणी)", "gom", false, 60),
        Language("korean", "Korean", "(한국어)", "ko", true, 61),
        Language("kusdish", "Kurdish ", "(Kurdí)", "ku", false, 62),
        Language("kyrgyz", "Kyrgyz", "(Кыргыз тили)", "ky", false, 63),
        Language("lao", "Lao", "(ພາສາລາວ)", "lo", false, 64),
        Language("latin", "Latin ", "(lingua latīna)", "la", true, 65),
        Language("latvian", "Latvian", "(latviešu valoda)", "lv", true, 66),
        Language("lithuanian", "Lithuanian", "(lietuvių kalba)", "it", false, 67),
        Language("lingala", "Lingala", "(lingála)", "ln", false, 68),
        Language("luxembourgish", "Luxembourgish", "(Lëtzebuergesch)", "ib", false, 69),
        Language("northmacedonia", "Macedonian", "(македонски јазик)", "mk", true, 70),
        Language("maithili", "Maithili", "(मैथिली)", "mai", false, 71),
        Language("malagasy", "Malagasy", "(Malagasy)", "mg", false, 72),
        Language("malay", "Malay", "(Bahasa melayu)", "ms", true, 73),
        Language("malayalam", "Malayalam", "(മലയാളം)", "ml", true, 74),
        Language("maltese", "Maltese", "(Malti)", "mt", false, 75),
        Language("maori", "Maori", "(Māori)", "mi", false, 76),
        Language("marathi", "Marathi", "(मराठी)", "mr", true, 77),
        Language("mongolian", "Mongolian", "(монгол хэл)", "mn", false, 78),
        Language("myanmar", "Myanmar_(Burmese)", "(မြန်မာစာ)", "my", true, 79),
        Language("nepali", "Nepali", "(नेपाली)", "ne", true, 80),
        Language("norwegian", "Norwegian", "(Norsk)", "no", true, 81),
        Language("nyanja", "Nyanja", "(Nyanja)", "ny", false, 82),
        Language("odia", "Odia", " (ଓଡ଼ିଆ)", "or", false, 83),
        Language("oromo", "Oromo", "(Afaan Oromo)", "om", false, 84),
        Language("pashto", "Pashto", "( پښتو)", "ps", false, 85),
        Language("persian", "Persian", "( فارسى)", "fa", false, 86),
        Language("dari_persian", "Persian Dari", "( فارسى)", "fa", false, 87),
        Language("polish", "Polish", "(polski)", "pl", true, 88),
        Language("portuguese", "Portuguese", "(português)", "pt", true, 89),
        Language("punjabi", "Punjabi", "(ਪੰਜਾਬੀ)", "pa", false, 90),
        Language("quechua", "Quechua", "(Runa simi)", "qu", false, 91),
        Language("romania", "Romania", "(limba română)", "ro", true, 92),
        Language("russian", "Russian", "(русский язык)", "ru", true, 93),
        Language("samon", "Samoan", "(Gagana Samoa)", "sm", false, 94),
        Language("sanskrit", "Sanskrit", "(संस्कृतम्)", "sa", false, 95),
        Language("scots_gaelic", "Scots Gaelic", "(Gàidhlig)", "gd", false, 96),
        Language("sepedi", "Sepedi", "(Sepedi)", "nso", false, 97),
        Language("serbian", "Serbian ", "(српски)", "sr", true, 98),
        Language("sesotho", "Sesotho", "(Sesotho)", "st", false, 99),
        Language("shona", "Shona", "(chiShona)", "sn", false, 100),
        Language("sindhi", "Sindhi", "( سنڌي)", "sd", false, 101),
        Language("sinhala", "Sinhala", "(සිංහල)", "si", true, ERROR_BANNER_SIZE_MISMATCH),
        Language("slovak", "Slovak", "(slovenski jezik)", "sk", true, 103),
        Language("slovenia", "Slovenian", "(slovenčina)", "sl", false, ERROR_EMPTY_BID_TOKEN),
        Language("somali", "Somali", "(af Soomaali)", "so", false, ERROR_AD_ALREADY_REQUESTED),
        Language("spanish", "Spanish", "(español)", "es", true, ERROR_PRESENTATION_AD_NOT_READY),
        Language("sundanese", "Sundanese", "(Basa Sunda)", "su", true, 107),
        Language("swahili", "Swahili", "(Kiswahili)", "sw", true, ERROR_AD_FORMAT_UNSUPPORTED),
        Language("sweden", "Swedish", "(Svenska)", "sv", true, 109),
        Language("tajik", "Tajik", "(тоҷики)", "tg", false, ERROR_INVALID_SERVER_PARAMETERS),
        Language("tamil", "Tamil", "(தமிழ்)", "ta", true, 111),
        Language("tatar", "Tatar", "(татар теле)", "tt", false, 112),
        Language("telugu", "Telugu ", "(తెలుగు)", "te", true, 113),
        Language("thai", "Thai", "(ภาษาไทย)", "th", true, 114),
        Language("tigrinya", "Tigrinya", "(ትግርኛ)", "ti", false, 115),
        Language("tsonga", "Tsonga", "(Xitsonga)", "ts", false, 116),
        Language("turkish", "Turkish", "(Türkçe)", "tr", true, MODULE_VERSION),
        Language("turkmen", "Turkmen", "(түркmенче)", "tk", false, 118),
        Language("ukrainian", "Ukrainian", "(Українська)", "uk", true, 119),
        Language("urdu", "Urdu", "(اردو)", "ur", true, 120),
        Language("uyghur", "Uyghur", "(ئۇيغۇر تىلى)", "ug", false, 121),
        Language("uzbek", "Uzbek", "(o’zbek tili)", "uz", false, 122),
        Language("vietnam", "Vietnamese", "(tiếng việt)", "vi", true, 123),
        Language("welsh", "Welsh", "(Cymraeg)", "cy", true, 124),
        Language("xhosa", "Xhosa", "(isiXhosa)", "xh", false, 125),
        Language("yiddish", "Yiddish", "( ײִדיש)", "yi", false, 126),
        Language("yoyuba", "Yoruba ", "(Yorùbá)", "yo", false, 127),
        Language("zulu", "Zulu", "(isiZulu)", "zu", false, 128),
    )

    val idiomsJson = """
        {
          "data": {
            "Age": [
              {
                "Idiom": "Age out of something",
                "Define": "The phrase age out of something means to reach an age at which one is no longer eligible for the system of care designed to provide services, such as education or protection, for people below a certain age level.",
                "Ex": "He has aged out of the special student scholarship program."
              },
              {
                "Idiom": "Dog's age",
                "Define": "The idiom dog's age refers to a long period of time.",
                "Ex": "We haven't seen Aunt Sophie for a dog's age (for years). It's been a dog's age since I worked one of these machines, but I'll give it a shot!"
              },
              {
                "Idiom": "Feel your age",
                "Define": "The phrase feel your age means to realize that you are getting old, especially compared with people you are with who are younger than you.",
                "Ex": "I really felt my age at work. All my colleagues looked very young."
              },
              {
                "Idiom": "Get on in years",
                "Define": "Old; advanced in age. ",
                "Ex": "Although she's getting on in years, she still looks young. My grandmother is getting on in years. She's no longer able to look after her home without help."
              },
              {
                "Idiom": "Golden age",
                "Define": "A period of or defined by outstanding excellence, quality, prosperity, or achievement",
                "Ex": "He said the decade after World War II was Canada's golden age."
              },
              {
                "Idiom": "In a coon's age",
                "Define": "Also, a dog's age. The phrase in a coon's age means in a very long time. The word coon refers to a raccoon, an omnivorous mammal, native to the Americas.",
                "Ex": "I haven't seen Sam in a coon's age."
              },
              {
                "Idiom": "In this day and age",
                "Define": "Also (in) this day and time: In the present. Usage notes - used to emphasize a difference between this time and time past",
                "Ex": "You can't afford to run businesses inefficiently in this day and age. Now, in this day and age you must have computer skills if you want to get a job. The girl was held under conditions that are hard to imagine in this day and time."
              },
              {
                "Idiom": "Of a certain age",
                "Define": "Said about people who are not young. Usage notes - used to avoid saying middle aged or old",
                "Ex": "It's a clothes boutique which caters for women of a certain age."
              },
              {
                "Idiom": "Of advanced age",
                "Define": "The phrase of advanced age or advanced years describes someone as old.",
                "Ex": "The conference is about the effect of advanced age on fertility and pregnancy in women."
              },
              {
                "Idiom": "Of age",
                "Define": "Old enough to be considered an adult.",
                "Ex": "Now that Mary is of age, she can buy her own car. When I'm of age, I'm going to get married and move to the city."
              },
              {
                "Idiom": "Put years on",
                "Define": "If something puts years on somebody, it makes them look or feel much older.",
                "Ex": "The breakup of his marriage put years on him."
              },
              {
                "Idiom": "Ripe old age",
                "Define": "Very old age.",
                "Ex": "Mr. Smith died last night, but he lived to a ripe old age of 99. All the Smiths seem to reach a ripe old age."
              },
              {
                "Idiom": "Tender age",
                "Define": "A young age.",
                "Ex": "She left home at the tender age of 17 and got married to a rock singer."
              },
              {
                "Idiom": "Under age",
                "Define": "The phrase under age means to be too young to be eligible for something.",
                "Ex": "Because she was under age, her parents were still responsible for her,under age smoking."
              }
            ],
            "Animals": [
              {
                "Idiom": "As gentle as a lamb",
                "Define": "Said about kind , innocent, mild-mannered people.",
                "Ex": "I thought she was gentle as a lamb until I heard her shouting at Richard."
              },
              {
                "Idiom": "Back the wrong horse",
                "Define": "1. To support someone or something that later cannot be successful. 2. To support a person or thing that fails.",
                "Ex": "1. I don't want to back the wrong horse, but it seems to me that Jed is the better candidate. Fred backed the wrong horse in the budget hearings. 2. It was only after we'd invested all the money that we discovered we'd been backing the wrong horse."
              },
              {
                "Idiom": "Be a chicken",
                "Define": "Be a coward. ",
                "Ex": "Come on, let’s go. Don’t be a chicken.There's nothing to be scared of. Don't be a chicken."
              },
              {
                "Idiom": "Be a cold fish",
                "Define": "Be a person who is distant and unfeeling ",
                "Ex": "He rarely talks to his colleagues. He's a cold fish. "
              },
              {
                "Idiom": "Be like a fish out of water",
                "Define": "Feel uncomfortable because you are in an unfamiliar situation.",
                "Ex": "I don't like going to the big parties they have. I always feel like a fish out of water there. Todd is a country boy raised in a small town in Idaho and was like a fish out of water when he visited New York City."
              },
              {
                "Idiom": "Cash cow",
                "Define": "This idioms refers to someone or something that generates a steady return of profits; a moneymaker. The term cash cow is a metaphor for a dairy cow used on farms to produce milk, offering a steady stream of income with little maintenance.",
                "Ex": "1. The typewriters production which had been their cash cow for so many years witnessed a collapse of sales. 2. The young actress turned out to be the cash cow for most Hollywood studios."
              },
              {
                "Idiom": "Cat got your tongue",
                "Define": "Something you say to someone when you are annoyed because they will not speak.",
                "Ex": "What's the matter  Has the cat got your tongue "
              },
              {
                "Idiom": "Chicken-hearted",
                "Define": "1. Cowardly and, 2. Not brave.",
                "Ex": "1. Yes, I'm a chicken-hearted softie. I never try anything too risky. 2. These chicken-hearted bosses always seem to give in at the first sign of a strike."
              },
              {
                "Idiom": "Count one's chickens before they hatch",
                "Define": "Make plans based on events that may or may not happen.",
                "Ex": "You might not win the prize and you've already spent the money  Don't count your chickens before they hatch! I know you have big plans for your consulting business, but don't count your chickens."
              },
              {
                "Idiom": "Curiosity killed the cat",
                "Define": "1. Being curious can get you into trouble. 2. Something that you say in order to warn someone not to ask too many questions about something. 3. It's best to mind one's own business.",
                "Ex": "1. Jill: Where did you get all that money  Jane: Curiosity killed the cat. 2. 'Why are you going away so suddenly ' 'Curiosity killed the cat.' 3. Don't ask about his divorce-curiosity killed the cat."
              },
              {
                "Idiom": "Dog eat dog",
                "Define": "Said about a world where people do anything to be successful.",
                "Ex": "It's disheartening to know that we are living in a dog eat dog world."
              },
              {
                "Idiom": "Dogs are barking",
                "Define": "If your dogs are barking, this means that your feet are hurting. Interesting fact - There is a brand of shoes called Hush Puppy. The connection between this brand and the expression dogs are barking is obvious: the shoes Hush Puppies are supposedly so comfortable and your feet won't hurt when you wear them.",
                "Ex": "My dogs are barking because I walked ten miles."
              },
              {
                "Idiom": "Drink like a fish",
                "Define": "To drink alcohol excessively.",
                "Ex": "Jeff really drank like a fish at the party on Saturday. I worry about Nancy; she drinks like a fish."
              },
              {
                "Idiom": "Every dog has its day",
                "Define": "1. Everyone gets a chance eventually. 2. Something that you say which means that everyone is successful during some period in their life. 3. Even the lowest will sometimes come to the fore.",
                "Ex": "1. Don't worry, you'll get chosen for the team. Every dog has its day. You may become famous someday. 2. He'll get that promotion eventually. Every dog has its day. 3. They may not listen to me now, but just wait, every dog has its day."
              },
              {
                "Idiom": "Fall prey to",
                "Define": "(Also fall victim to) 1. To be harmed by someone or something. 2. To be put into such a vulnerable position as to be at risk of harm, destruction, or invasion.",
                "Ex": "1. We worry that our children will fall prey to the influence of bad kids. Patients may fall prey to dishonest sales-people who say they can cure their pain. 2. A person who fell prey to swindlers; did not want the country to fall prey to terrorists."
              },
              {
                "Idiom": "Fight like cat and dog",
                "Define": "To argue and fight violently.",
                "Ex": "We get on very well as adults but as kids we fought like cat and dog."
              },
              {
                "Idiom": "Grouse about someone or something",
                "Define": "To complain about someone or something.",
                "Ex": "What are you grousing about now  I am grousing about your carelessness!"
              },
              {
                "Idiom": "Have bigger fish to fry",
                "Define": "1. To have other things to do; to have more important things to do. 2. To have something more important or more interesting to do.",
                "Ex": "1. I can't take time for your problem. I have other fish to fry. I won't waste time on your question. I have bigger fish to fry. 2. I couldn't spend a lot of time on the problem. I had other fish to fry."
              },
              {
                "Idiom": "Have kittens ",
                "Define": "(Also have a cow) 1. To get extremely upset. 2. To become very worried or upset about something.",
                "Ex": "1. My mother pretty near had kittens when she found out I got fired. Calm down. Don't have kittens. 2. She nearly had kittens when I said I was going to buy a motorbike."
              },
              {
                "Idiom": "Help a lame dog over a stile",
                "Define": "Said about someone who helps people who are in difficulty or trouble.",
                "Ex": "You can trust him. He always helps a lame dog over a stile."
              },
              {
                "Idiom": "Horse of a different color",
                "Define": "(Also horse of another color) A situation or a subject that is different from what you had first thought it was.",
                "Ex": "You said you didn't like going to the movies, but if you don't want to go because you're broke, that's a horse of another color."
              },
              {
                "Idiom": "In two shakes of a lamb's tail",
                "Define": "In a very short time; very quickly.",
                "Ex": "I'll be with you in two shakes."
              },
              {
                "Idiom": "Keep the wolf from the door",
                "Define": "1. To maintain oneself at a minimal level; to keep from starving, freezing. 2. To have enough money to be able to eat and live. 3. Ward off starvation or financial ruin. 4. To avoid the privation and suffering resulting from a lack of money.",
                "Ex": "1. I don't make a lot of money, just enough to keep the wolf from the door. We have a small amount of money saved, hardly enough to keep the wolf from the door. 2. Forty percent of the country's population receive part-time wages that barely keep the wolf from the door. 3. In many countries people are working simply to keep the wolf from the door, and owning a car or washing machine is just a dream , or Gail would take any job now, just to keep the wolf from the door. This term alludes to the wolf's fabled ravenousness. [Mid-1500s]. 4. Both spouses had to work in order to keep the wolf from the door."
              },
              {
                "Idiom": "Lame duck",
                "Define": "Someone or something that is disabled, helpless, ineffective, or inefficient.",
                "Ex": "You can't expect much from a lame duck. As a lame duck, there's not a lot I can do."
              },
              {
                "Idiom": "Like water off a duck's back",
                "Define": "Easily; without any apparent effect.",
                "Ex": "Insults rolled off John like water off a duck's back. The bullets had no effect on the steel door. They fell away like water off a duck's back."
              },
              {
                "Idiom": "Look like mutton dressed as lamb",
                "Define": "An offensive way of saying that a woman is dressed in a style that is more suitable for a much younger woman",
                "Ex": "Do you think this skirt is too short  I don't want to look like mutton dressed as lamb."
              },
              {
                "Idiom": "Not have a cat in hell's chance",
                "Define": " (Also not have a snowball's chance in hell) Not to be able to achieve something.",
                "Ex": "They haven't a cat in hell's chance of getting over the mountain in weather like this."
              },
              {
                "Idiom": "Play cat and mouse",
                "Define": "To tease, confuse or fool someone by trying to trick them into making a mistake so that you have an advantage over them. ",
                "Ex": "The famous businessman spent his time playing cat and mouse with the judge. "
              },
              {
                "Idiom": "Put the cat among the pigeons",
                "Define": "To do or say something that causes trouble and makes a lot of people angry or worried",
                "Ex": "Tell them all they've got to work on Saturday. That should set the cat among the pigeons."
              },
              {
                "Idiom": "Rabbit hole",
                "Define": "To enter into a situation or begin a process or journey that is particularly strange, problematic, difficult, complex, or chaotic, especially one that becomes increasingly so as it develops or unfolds. (An allusion to Alice's Adventures in Wonderland by Lewis Carroll.)",
                "Ex": "Owning your own business is a huge responsibility that not everyone is prepared for. Are you sure you're ready to go down the rabbit hole  I've stayed away from drugs and alcohol since coming to college. I have an addictive personality, so I decided to just avoid going down that rabbit hole altogether. Overhauling the current tax legislation is a rabbit hole I don't think this administration should go down at this point."
              },
              {
                "Idiom": "Rain cats and dogs",
                "Define": "To rain very hard, heavily",
                "Ex": "It's raining cats and dogs. Look at it pour! I'm not going out in that storm. It's raining cats and dogs."
              },
              {
                "Idiom": "Sacred cow",
                "Define": "Something that is regarded by some people with such respect and veneration that they do not like it being criticized by anyone in any way. (From the fact that the cow is regarded as sacred in India and is not eaten or mistreated).",
                "Ex": "A university education is a sacred cow in the Smith family. Fred is regarded as a failure because he quit school at 16. Don't talk about eating meat to Pam. Vegetarianism is one of her sacred cows."
              },
              {
                "Idiom": "Scaredy cat",
                "Define": "Someone who is frightened when there is no reason to be. Usage notes - This phrase is used especially by children.",
                "Ex": "Go on you scaredy-cat, jump in."
              },
              {
                "Idiom": "See a man about a dog",
                "Define": "Used as an excuse for leaving without giving the real reason (especially if the reason is to go to the toilet, or to have a drink) ",
                "Ex": "Please, wait for me here. I won't be long. I'm just going to see a man about a dog. "
              },
              {
                "Idiom": "Sitting duck",
                "Define": "Someone or something vulnerable to attack, physical or verbal",
                "Ex": "Because of his unpopular opinions about foreign policy, he made of himself a sitting duck. "
              },
              {
                "Idiom": "Spring chicken",
                "Define": "To be old; not young anymore. ",
                "Ex": "She is not a spring chicken. She wouldn't like to go partying with us."
              },
              {
                "Idiom": "Take to something like a duck to water",
                "Define": "To have a natural ability to do something.",
                "Ex": "She took to motherhood like a duck to water."
              },
              {
                "Idiom": "The straw that broke the camel’s back",
                "Define": "A small and seemingly insignificant addition to a burden that renders it too much to bear; the small thing which causes failure, or causes inability or unwillingness to endure any more of something.",
                "Ex": "When the boss saw him coming late to work. That was the straw that broke the camel’s back. He fired her immediately."
              },
              {
                "Idiom": "To sell wolf tickets",
                "Define": "To make empty threats or promises; to bluff.",
                "Ex": "You're selling wolf tickets."
              },
              {
                "Idiom": "When pigs fly",
                "Define": "If you say when pigs fly you mean that something will never happen. This phrase is used presumably due to the unlikelihood that pigs will ever evolve wings.",
                "Ex": "Sure she'll help us clean the house when pigs fly. "
              }
            ],
            "Clothes": [
              {
                "Idiom": "All dressed up and nowhere to go",
                "Define": "The phrase all dressed up and nowhere to go means getting ready for something and then it never happened. The phrase may be used literally or figuratively.",
                "Ex": "She was waiting for him but he never showed up. As usual, she was all dressed up and nowhere to go."
              },
              {
                "Idiom": "All hat and no cattle",
                "Define": "Describing someone who is full of big talk but lacking action, power, or substance; pretentious.",
                "Ex": "We expect our president to be effective in his job, not a person who is all hat and no cattle."
              },
              {
                "Idiom": "At the drop of a hat",
                "Define": "When someone does something at the drop of a hat, they do it without delay or good reason.",
                "Ex": "So many years of sacrifice and then you can leave me at the drop of a hat."
              },
              {
                "Idiom": "Be in somebody's shoes",
                "Define": "To be in the situation that another person is in.",
                "Ex": "I wouldn't like to be in Nancy's shoes. She'll have a lot of problems with her boss."
              },
              {
                "Idiom": "Big girl's blouse",
                "Define": "The phrase a big girl's blouse is a British and Australian idiomatic expression which refers to an effeminate or weak man or boy.",
                "Ex": "Don't care about what he is saying! Let's go! He's just a big girl's blouse. "
              },
              {
                "Idiom": "Birthday suit",
                "Define": "The idiom birthday suit is a slang term for the naked human body.",
                "Ex": "It was embarrassing for her to be seen in her birthday suit."
              },
              {
                "Idiom": "Boots on the ground",
                "Define": "The ground forces actually fighting in a war or conflict at the time of speaking, rather than troops not engaged or being transported to the fighting.",
                "Ex": "The Pentagon may say we have enough, but that's not what I'm hearing from the boots on the ground."
              },
              {
                "Idiom": "Handle someone with kid gloves",
                "Define": "To be very careful and polite to a person because you don't want to hurt him or her, or you don't want to make him or her angry or upset.",
                "Ex": "He has become so sensitive after his divorce. You need to handle him with kid gloves."
              },
              {
                "Idiom": "Hat in hand",
                "Define": "The phrase hat in hand means to ask someone for a favor with humility. Another variation of the idiom is cap in hand",
                "Ex": "She came back hat in hand asking for forgiveness. "
              },
              {
                "Idiom": "Have a card up your sleeve",
                "Define": "To have a secret plan.",
                "Ex": "She still has got something up her sleeve, and it should solve all her problems."
              },
              {
                "Idiom": "I'll eat my hat",
                "Define": "Said to suggest that you will be surprised if something happens.",
                "Ex": "If his business becomes successful, I'll eat my hat."
              },
              {
                "Idiom": "If the shoe fits, wear it",
                "Define": "The phrase if the shoe fits, wear it means if something applies to you, then accept it. This expression originated as if the cap fits and dates from the early 1700s.",
                "Ex": "Lacy: The teacher says that I need to spend more time with my son. Nancy: Well, if the shoe fits, wear it."
              },
              {
                "Idiom": "In someone's pocket",
                "Define": "The phrase to be in someone's pocket means to be dependent on someone financially and consequently under their influence.",
                "Ex": "The committee must surely have been in his pocket."
              },
              {
                "Idiom": "Laugh up your sleeve",
                "Define": "To be secretly amused.",
                "Ex": "They're very polite in your presence, but you get the feeling they're laughing up their sleeves. "
              },
              {
                "Idiom": "Lick someone's boots",
                "Define": "The phrase lick someone's boots means to act in a servile or obsequious way toward someone, especially to gain favor from them. Shakespeare used this idiom in the form of lick someone's shoe in The Tempest (3:2) when Caliban wants to serve Stephano rather than Trinculo, offering to lick his shoe CALIBAN How does thy honour  Let me lick thy shoe. I'll not serve him; he's not valiant.",
                "Ex": " She seizes every opportunity to lick the boss's boots."
              },
              {
                "Idiom": "Light skirt",
                "Define": "The phrase light skirt refers to a loose woman, a prostitute.",
                "Ex": "Don't call her a light skirt. She is a respectable woman."
              },
              {
                "Idiom": "Lose your shirt",
                "Define": "To lose all your money as a result of gambling or bad investment.",
                "Ex": "He lost his shirt yeterday in the casino."
              },
              {
                "Idiom": "Pass the hat around",
                "Define": "(Also pass the hat round) To collect money by asking people or organizations.",
                "Ex": "They passed the hat round as they needed money to rebuild the poor neighbors' house. "
              },
              {
                "Idiom": "Put yourself in someone's shoes",
                "Define": "To see how it feels when you put yourself in smoeone's place.",
                "Ex": "What could I have done to solve the problem  Just put yourself in my shoes."
              },
              {
                "Idiom": "Quake in one's boots",
                "Define": "To be frightened, scared, or nervous.",
                "Ex": "The thought of climbing that high had him quaking in his boots."
              },
              {
                "Idiom": "Step into somebody's shoes",
                "Define": "To take over someone's place or job.",
                "Ex": "Who do you think will step into Leila's shoes when she leaves "
              },
              {
                "Idiom": "Take one's hat off to someone",
                "Define": "Said when you admire someone for an achievement.",
                "Ex": "If she manages to deal with three small children and a full-time job, I'll take my hat off to her. "
              },
              {
                "Idiom": "Talk through one's hat",
                "Define": "To talk nonesense",
                "Ex": "He was talking through his hat. I couldn't understand what he was saying."
              },
              {
                "Idiom": "The boot is on the other foot",
                "Define": "(Also the shoe is on the other foot) Said about a change of positions when someone whon was in a weaker situation is now in strong position.",
                "Ex": "Don't expect him to make any more changes in the management of the company, especially after his appointment as the new manager of the project. The boot is on the other foot."
              },
              {
                "Idiom": "The men in grey suits",
                "Define": "The phrase the men in grey suits refers to the powerful and influential men in business or politics. A variation of this idiom is: the men in suits",
                "Ex": "The men in grey suits will decide the future of this nation."
              },
              {
                "Idiom": "Throw your hat into the ring",
                "Define": "(Also toss your hat into the ring) To show your intention to enter a competition.",
                "Ex": "Nearly a year before the elections, he threw his hat into the ring."
              },
              {
                "Idiom": "Tighten your belt",
                "Define": "The idiom tighten your belt means to try to spend less money or use less resources",
                "Ex": "Going on holiday abroad cost us a lot of money so we're all going to have to tighten our belts."
              },
              {
                "Idiom": "Under one's belt",
                "Define": "The idiom under your belt refers to something that you have learned, mastered or achieved and that might be an advantage for you in the future.",
                "Ex": "I see that you already have the techniques of drawing under your belt."
              },
              {
                "Idiom": "Wait for the other shoe to drop",
                "Define": "To await a seemingly inevitable event, especially one which is not desirable.",
                "Ex": "He was waiting for the other shoe to drop once some of his companions had been captured by the official authorities,"
              },
              {
                "Idiom": "Wear the pants",
                "Define": "(Also wear the trousers) Especially of a woman - to exercise authority or to be the person in charge in a relationship.",
                "Ex": "He may seem authoritative, but the truth is that it's his wife who really wears the pants in that relationship."
              },
              {
                "Idiom": "Wear your heart on your sleeve",
                "Define": "To display one's feelings openly.",
                "Ex": "Alan always has his heart on his sleeve. Everybody knows how he feels. "
              }
            ],
            "Colors": [
              {
                "Idiom": "All cats are grey in the dark",
                "Define": "The phrase all cats are grey in the dark means that in the dark, physical appearance is unimportant. The phrase is attributed to Benjamin Franklin.",
                "Ex": "I really don't care if she is ugly. All cats are gray in the dark."
              },
              {
                "Idiom": "Be in black and white",
                "Define": "(Also be down in black and white) to be written down.",
                "Ex": "My conditions to accept the job were in black and white in the contract."
              },
              {
                "Idiom": "Black and blue",
                "Define": "Covered in bruises",
                "Ex": "He was black and blue the day after the accident"
              },
              {
                "Idiom": "Black sheep",
                "Define": "A disreputable member of a family or a group.",
                "Ex": "They say he's the black sheep of the Bakers."
              },
              {
                "Idiom": "Black and white",
                "Define": "Said when you have a simplistic opinion about situations while they are in fact more complicated.",
                "Ex": "I think terrorism isn't a black and white issue. "
              },
              {
                "Idiom": "Blue-eyed boy",
                "Define": "(Also fair-haired boy) a person highly regarded by someone and treated with special favor",
                "Ex": "He was the blue-eyed boy of the boss."
              },
              {
                "Idiom": "Born to the purple",
                "Define": "If someone is born to the purple or in the purple, they are born into a reigning family or privileged class.",
                "Ex": "She was the only child born to the purple."
              },
              {
                "Idiom": "Browned off",
                "Define": "Annoyed, upset, angry, bored, fed up, disgusted.",
                "Ex": "He was browned off when he was ill treated."
              },
              {
                "Idiom": "Fair-haired boy",
                "Define": "(Also blue-eyed boy) a person highly regarded and by someone or a group and treated with special favor",
                "Ex": "Before he was fired out, he had been the fair-haired boy of the boss."
              },
              {
                "Idiom": "Feel blue",
                "Define": "To feel sad.",
                "Ex": "She felt blue after her divorce."
              },
              {
                "Idiom": "Golden opportunity",
                "Define": "Ideal moment to do something. ",
                "Ex": "She missed a golden opportunity to prepare herself for a career as a doctor, when she didn't continue her studies at university."
              },
              {
                "Idiom": "Green light",
                "Define": "Permission to go ahead with something such as a project.",
                "Ex": "As a result of the severe drought, the government has given the green light for importing cereals."
              },
              {
                "Idiom": "Green with envy",
                "Define": "Consumed by envy; envious to the point where it is noticeable to others. ",
                "Ex": "She was green with envy when she saw my new car."
              },
              {
                "Idiom": "Green-eyed monster",
                "Define": "Envy, jealousy, covetousness ",
                "Ex": "O, beware, my lord, of jealousy; It is the green-eyed monster which doth mock. The meat it feeds on. William Shakespeare. 2. His success aroused the green-eyed monster in his friend."
              },
              {
                "Idiom": "In the pink",
                "Define": "Healthy.",
                "Ex": "He has been in the pink since he decided to go on a diet and exercise regularly."
              },
              {
                "Idiom": "Out of the blue",
                "Define": "Something which is totally unexpected.",
                "Ex": "She sent him a letter, out of the blue, telling him that she was in love with another guy."
              },
              {
                "Idiom": "Red flag",
                "Define": "A sign or signal that something is wrong cue, it is a warning, or alert.",
                "Ex": "She considered that playing with her feelings was a red flag. She wouldn't tolerate anyone to do so."
              },
              {
                "Idiom": "Red-handed",
                "Define": "Be discovered in or just after the act of doing something wrong or illegal",
                "Ex": "She was caught red-handed, stealing a ring."
              },
              {
                "Idiom": "Red ink",
                "Define": "A euphemism for financial loss.",
                "Ex": "There is too much red ink in the company's financial statement."
              },
              {
                "Idiom": "Red-letter day",
                "Define": "A particularly significant day (personal or sectarian), usually very positive, sometimes very negative.",
                "Ex": "Monday was a red letter day for her. She accomplished a lot and had fun doing it."
              },
              {
                "Idiom": "Scream blue murder",
                "Define": "(Also shout blue murder or scream bloody murder) to shout or complain loudly because you are annoyed about something.",
                "Ex": "Because he didn't get what he wanted, he screamed blue murder."
              },
              {
                "Idiom": "Until one is blue in the face",
                "Define": "(Talk OR Say something OR Shout until one is blue in the face) Pointless efforts while trying to convince someone or change his mind.",
                "Ex": "His parents tell him to do his homework until they are blue in the face. In fact, he never does his homework."
              },
              {
                "Idiom": "Whiter than white",
                "Define": "Said about someone who is totally fair and honest.",
                "Ex": "That little boy is whiter than white. He never does anything wrong."
              },
              {
                "Idiom": "Yellow journalism",
                "Define": "Journalism which is sensationalistic and of questionable accuracy and taste.",
                "Ex": "This paper is practising yellow journalism with its reports on sex scandals. "
              }
            ],
            "Crime": [
              {
                "Idiom": "A steal",
                "Define": "A steal refers to a good deal; it's almost like you stole it.",
                "Ex": "You look great in these shoes! They’re at 70% discount! I paid Just ${'$'}30! Wow that’s really a steal! It's such a great deal."
              },
              {
                "Idiom": "Beat the rap",
                "Define": "To beat the rap means to evade conviction and punishment for a crime.",
                "Ex": "The lawyer helped John beat the rap after being caught by the police."
              },
              {
                "Idiom": "Behind bars",
                "Define": "To be in prison.",
                "Ex": "He spent three years behind bars."
              },
              {
                "Idiom": "Cat burglar",
                "Define": "A cat burglar refers to a thief who enters a building by skillfully climbing to a building without attracting notice.",
                "Ex": "He was known for being a cat burglar who only stole from apartments in high buildings."
              },
              {
                "Idiom": "Crime doesn't pay",
                "Define": "This idiom is used to suggest that crime will ultimately not benefit a person.",
                "Ex": "Doing something illegal to get money may be tempting for some, but in fact crime doesn't pay."
              },
              {
                "Idiom": "Highway robbery",
                "Define": "This expression is used to refer to a price or a fee that is exorbitantly high.",
                "Ex": "The price for wine in this restaurant is simply highway robbery."
              },
              {
                "Idiom": "In the dock",
                "Define": "To be on trial in court.",
                "Ex": "The accused stood in the dock."
              },
              {
                "Idiom": "On the run",
                "Define": "Fleeing or running from the police.",
                "Ex": "The murderer is still on the run."
              },
              {
                "Idiom": "On the take",
                "Define": "This idiom is used to describe a person who is in a position of authority and takes or seeks to take bribes or illegal income.",
                "Ex": "This is a country where many officials are on the take."
              },
              {
                "Idiom": "Poverty is not a crime",
                "Define": "(Also poverty is no sin) This expression is used to mean that it is not a crime to be poor and that we shouldn't condemn people for their poverty.",
                "Ex": "I don't know why the police are chasing those poor people out of town. Poverty is not a crime."
              },
              {
                "Idiom": "Scream bloody murder",
                "Define": "If you scream bloody murder, you protest loudly and angrily as if something very serious has happened.mAnother variation of the idiom is yell OR cry bloody murder.",
                "Ex": "There is no point in screaming bloody murder about the new law."
              },
              {
                "Idiom": "Serve time",
                "Define": "Saying that someone is serving time means that he is in prison.",
                "Ex": "After the gangster had served his time in jail, he got married and found a job. "
              },
              {
                "Idiom": "Set a thief to catch a thief",
                "Define": "The best person to catch a thief is another thief, because he or she knows how thieves think.",
                "Ex": "The government set a thief to catch a thief. They hired a hacker to entrap other hackers who tried to break into the Pentagon's databases."
              },
              {
                "Idiom": "Stool pigeon",
                "Define": "A decoy or an informer, especially one who is a spy for the police. ",
                "Ex": "He was killed by a gangster because he was thought to be a stool pigeon. "
              },
              {
                "Idiom": "The weed of crime bears bitter fruit",
                "Define": "The phrase the weed of crime bears bitter fruit means that nothing good comes from criminal schemes. The idiom comes from The Shadow radio drama broadcasted in the 1930s. The program is well-remembered for those episodes voiced by Orson Welles. The episodes start with - Who knows what evil lurks in the hearts of me. The Shadow knows - At the end of each episode The Shadow reminded listeners that - The weed of crime bears bitter fruit. Crime does not pay... The Shadow knows!",
                "Ex": "Don't mislead yourself. You will pay for your crimes one day; the weed of crime bears bitter fruit. "
              },
              {
                "Idiom": "There is honor among thieves",
                "Define": "When you say there is honor among thieves, this means that even among criminals there is honor and that they do not commit crimes against each other. ",
                "Ex": "The gangsters had a strong respect for their old boss which demonstrate that there is honor among thieves."
              },
              {
                "Idiom": "Thick as thieves",
                "Define": "Intimate, close-knit.",
                "Ex": "Alan and John attended a boarding school together and were thick as thieves."
              }
            ],
            "Death": [
              {
                "Idiom": "Another nail in one's coffin",
                "Define": "One in a series of events which lead to downfall or inevitable failure.",
                "Ex": "After the bankruptcy and the death of his only son, divorce is just another nail in his coffin."
              },
              {
                "Idiom": "At death's door",
                "Define": "About to die; in a life-threatening state of health ",
                "Ex": "There were rumors that the president was murdered, or at death's door."
              },
              {
                "Idiom": "At death's door",
                "Define": "If you say you are at death's door you mean that you are very close to the end of your life.",
                "Ex": "She was so ill and was at death's door for more than a month."
              },
              {
                "Idiom": "Be dead in the water",
                "Define": "The idiom dead in the water means that something is unsuccessful and it seems impossible that it will be successful in the future.",
                "Ex": "The whole economy is dead in the water. "
              },
              {
                "Idiom": "Between life and death",
                "Define": "A situation involving the danger of dying or being killed. ",
                "Ex": "The little kid lay all night long between life and death. "
              },
              {
                "Idiom": "Bite the dust",
                "Define": "The phrase bite the dust has two meanings:to die. 2. to break; to fail.   ",
                "Ex": "Too many soldiers bit the dust in the second world war. 2. My laptop finally bit the dust."
              },
              {
                "Idiom": "Breathe one's last",
                "Define": "The phrase breathe one's last means to die.",
                "Ex": "After he had suffered from leukemia for years, he breathed his last at about three o'clock in the morning."
              },
              {
                "Idiom": "Dance on someone's grave",
                "Define": "To celebrate a person's death triumphantly.",
                "Ex": "He said to his enemies that he would recover his strength and would surely dance on their graves."
              },
              {
                "Idiom": "Dead and buried",
                "Define": "No longer in use or under consideration, irrelevant, forgotten.",
                "Ex": "All past animosities are dead and buried now."
              },
              {
                "Idiom": "Dead meat",
                "Define": "Someone in danger of death or severe punishment.",
                "Ex": "You'll be dead meat if you go on treating these poor people like that."
              },
              {
                "Idiom": "Dead right",
                "Define": "If someone is dead right, it means that they are absolutely correct.",
                "Ex": "Nancy: His wife is really beautiful. Lacy: you're dead right!"
              },
              {
                "Idiom": "Dead to the world",
                "Define": "Said about someone who is sound asleep or unconscious.",
                "Ex": "He slept right through the night and was still dead to the world when I went out."
              },
              {
                "Idiom": "Dead wood",
                "Define": "Workers no longer contributing to an organization.",
                "Ex": "There's a lot of dead wood in this company."
              },
              {
                "Idiom": "Dice with death",
                "Define": "To do something which is very risky, or dangerous, and could even cause one's death.",
                "Ex": "Someone who drinks and drives is someone who dices with death."
              },
              {
                "Idiom": "Done to death",
                "Define": "If something is done to death, it is used or discussed so many times that it has become boring.",
                "Ex": "That theme has been done to death by generations of poets."
              },
              {
                "Idiom": "Kiss of death",
                "Define": "Something that may seem good and favourable but that actually brings ruin to hopes, plans, etc.",
                "Ex": "Her mariage to that guy was the kiss of death for Leila's happiness."
              },
              {
                "Idiom": "Love somebody to death",
                "Define": "To love somebody very much.",
                "Ex": "He loves her to death."
              },
              {
                "Idiom": "Meet one's death",
                "Define": "(Also meet one's end) To die. ",
                "Ex": "A friend of mine met his death when he got hit by a car."
              },
              {
                "Idiom": "Nothing is certain but death and taxes",
                "Define": "The phrase nothing is certain but death and taxes means that everything in life is uncertain. The only things that you can be sure of are:You will undoubtedly die. 2. You will certainly have to pay taxes.",
                "Ex": "Lacy: I can't believe how much tax money we have to pay for setting up this business. Alice: You know, nothing is certain but death and taxes. This saying also comes from the letters of Benjamin Franklin where he states: Our new Constitution is now established, and has an appearance that promises permanency; but in this world nothing can be said to be certain, except death and taxes. Benjamin Franklin, in a letter to Jean-Baptiste Leroy, 1789. However, Franklin's letter is not the origin of the phrase; it appeared earlier in Daniel Defoe's The History of the Devil: Things as certain as Death and Taxes, can be more firmly believ’d. Daniel Defoe The Political History of the Devil 1726. But it should be noted that the origin of the phrase dates back to Daniel Defoe's The History of the Devil: Things as certain as Death and Taxes, can be more firmly believ’d. Daniel Defoe The Political History of the Devil 1726."
              },
              {
                "Idiom": "Over my dead body",
                "Define": "Under no circumstances; absolutely not.",
                "Ex": "He wants to get all the money for himself. Well, it will be over my dead body!"
              },
              {
                "Idiom": "Wouldn't be caught dead",
                "Define": "(Also wouldn't be seen dead) Said about something that you would not like to do, or would rather die than do it. ",
                "Ex": "I would not be caught dead in such a miniskirt."
              }
            ],
            "Food": [
              {
                "Idiom": "About as useful as a chocolate teapot",
                "Define": "Saying something is about as useful as a chocolate teapot means that it is totally useless.",
                "Ex": "A car in a heavy traffic jam is as useful as a chocolate teapot. Use a bike instead!"
              },
              {
                "Idiom": "Above the salt",
                "Define": "If someone is above the salt they are of high standing or honor. The origin of the phrase dates back to the medieval times. Then salt which was a valuable seasoning was placed in the middle of a dining table and the lord and his family were seated above the salt and other guests or servants below.",
                "Ex": "In medieval times lords used to sit above the salt."
              },
              {
                "Idiom": "All one’s eggs in one basket",
                "Define": "The state of having invested heavily in just one area or of having devoted all of one’s resources to one thing.",
                "Ex": "The stock market decline wouldn’t have hurt him so badly if he hadn’t had all his eggs in one basket"
              },
              {
                "Idiom": "Apple of somebody's eye",
                "Define": "Said about someone whom you love the most and you are very proud of.",
                "Ex": "His son is the apple of his eye."
              },
              {
                "Idiom": "Bad egg",
                "Define": "Bad person",
                "Ex": "Just ignore him. He's a bad egg."
              },
              {
                "Idiom": "Banana republic",
                "Define": "A small country, especially one in Central America, that is dependent on a single export commodity (traditionally bananas) and that has a corrupt, dictatorial government.",
                "Ex": "Banana republic countries need democratization. "
              },
              {
                "Idiom": "Bear fruit",
                "Define": "The phrase bear fruit means to yield successful results.",
                "Ex": "He thinks his new plan will undoubtedly bear fruit."
              },
              {
                "Idiom": "Below the salt",
                "Define": "If someone is below the salt they are common or of low standing.",
                "Ex": "In medieval times servants used to sit below the salt."
              },
              {
                "Idiom": "Big Apple",
                "Define": "The phrase Big Apple is a nickname for New York City.",
                "Ex": "Have you visited the official website of the Big Apple  "
              },
              {
                "Idiom": "Bring home the bacon",
                "Define": "Get a job and bring home money earned from this job.",
                "Ex": "When her husband got fired, she decided to look for a job because someone's got to bring home the bacon. "
              },
              {
                "Idiom": "Butter up",
                "Define": "To praise or flatter excessively.",
                "Ex": "Why are you buttering up the boss  "
              },
              {
                "Idiom": "Carrot and stick",
                "Define": "An offer involving a reward countered by the threat of punishment.",
                "Ex": "The president took a carrot and stick approach to the protests against his new laws."
              },
              {
                "Idiom": "Chew the cud",
                "Define": "The idiom chew the cud means to ponder over or meditate about something; to think carefully about something.Cud refers to the food regurgitated from the stomach to the mouth of a ruminant animal such as a cow and chewed again.",
                "Ex": "She wanted to chew the cud before she could let them know about her decision. "
              },
              {
                "Idiom": "Chew the fat",
                "Define": "To waste time talking or to chat idly.",
                "Ex": "As she had nothing to do, she wasted time chewing the fat with the neighbor."
              },
              {
                "Idiom": "Cool as a cucumber",
                "Define": "Very calm.",
                "Ex": "It's amazing that he never shows his emotions even when he has a lot of problems. He's cool as a cucumber."
              },
              {
                "Idiom": "Couch potato",
                "Define": "Lazy person.",
                "Ex": "He is a couch potato."
              },
              {
                "Idiom": "Cream of the crop",
                "Define": "The phrase cream of the crop means the best of all.",
                "Ex": "Don't worry about your studies; you are the cream of the crop."
              },
              {
                "Idiom": "Eat humble pie",
                "Define": "(Also eat humble crow) said when you admit that you were wrong.",
                "Ex": "In the begining he boasted that she would get the best grade. But then she was forced to eat humble pie."
              },
              {
                "Idiom": "Eye candy",
                "Define": "A very attractive person or persons or any object or sight with considerable visual appeal.",
                "Ex": "1. I'm going to the beach to check out some eye candy. 2. The computer graphics added lots of eye candy to that movie."
              },
              {
                "Idiom": "Food for thought",
                "Define": "Information or knowledge that is worthy of contemplation.",
                "Ex": "The ideas developed in this book have certainly given me food for thought."
              },
              {
                "Idiom": "Forbidden fruit",
                "Define": "Illicit pleasure or something desired that cannot be had.",
                "Ex": "She has always been his forbidden fruit because she's his teacher."
              },
              {
                "Idiom": "Get out of a jam",
                "Define": "Get out of a bad situation.",
                "Ex": "I need some help getting out of a jam."
              },
              {
                "Idiom": "Go bananas",
                "Define": "To become very angry.",
                "Ex": "He went bananas when he heard the news."
              },
              {
                "Idiom": "Hot potato",
                "Define": "An awkward or delicate problem with which nobody wants to be associated.",
                "Ex": "Gun control in the United States has always been a hot potato for politicians."
              },
              {
                "Idiom": "In a nutshell",
                "Define": "In summary.",
                "Ex": "The truth in a nutshell is that I know nothing about what they want me to do in this job."
              },
              {
                "Idiom": "Into a jam",
                "Define": "(Also in a jam) in a difficult situation.",
                "Ex": "He found himself in a jam when he was caught cheating."
              },
              {
                "Idiom": "Like chalk and cheese",
                "Define": "(Also as chalk and cheese) When things or people are like chalk and cheese, they are different although they are superficially alike.",
                "Ex": "His two sons are like chalk and cheese."
              },
              {
                "Idiom": "Low-hanging fruit",
                "Define": "The phrase low-hanging fruit refers to something that is easily achieved or obtained or to something that can be obtained by readily available means.",
                "Ex": "After they started their small company, they wanted to go after the low-hanging fruit."
              },
              {
                "Idiom": "Separate the wheat from the chaff",
                "Define": "To separate things of value from things of no value.",
                "Ex": "We got a lot of applicants for the job. But we are trying to separate the wheat from the chaff. "
              },
              {
                "Idiom": "She'll be apples",
                "Define": "Everything will be all right. ",
                "Ex": "'What about our trip to the mountain. They say it will snow all night long' 'Don't worry. She'll be apples.'       "
              },
              {
                "Idiom": "Sour as vinegar",
                "Define": "The phrase sour as vinegar very sour and disagreeable.",
                "Ex": "1. This cheese is sour as vinegar. 2. Mike is sour as vinegar this morning."
              },
              {
                "Idiom": "Spill the beans",
                "Define": "To reveal a secret. ",
                "Ex": "Why did you spill the beans about our new project  It was supposed to be top secret. "
              },
              {
                "Idiom": "That's the way the cookie crumbles",
                "Define": "(Also that's the way the ball bounces) said to show that things don't always turn out the way we hope. ",
                "Ex": "In spite of her kindness she is the least popular in her class. But, that's the way the cookie crumbles. "
              },
              {
                "Idiom": "Traffic jam",
                "Define": "A lot of vehicles causing slow traffic. ",
                "Ex": "We got stuck in a traffic jam for more than an hour. "
              },
              {
                "Idiom": "Variety is the spice of life",
                "Define": "Frequent changes in one's life makes life interesting. ",
                "Ex": "They frequently change the furniture of the house because they think that variety is the spice of life."
              },
              {
                "Idiom": "Walk on eggs",
                "Define": "(Also walk on thin ice and walk on eggshells) be very carefully. ",
                "Ex": "I was walking on eggs when I told her about the truth."
              },
              {
                "Idiom": "Walk on eggshells",
                "Define": "To be overly careful in dealing with a person or situation; to be careful and sensitive, in handling very sensitive matters. ",
                "Ex": "He was walking on eggshells when he was talking to him about his wife."
              },
              {
                "Idiom": "You can catch more flies with honey than with vinegar",
                "Define": "The proverb you can catch more flies with honey than with vinegar means that it is much easier to get what you want by being polite rather than by being rude and insolent.",
                "Ex": "Just be polite when you ask for something. You know, you can catch more flies with honey than with vinegar."
              }
            ],
            "Furniture": [
              {
                "Idiom": "A watched pot never boils",
                "Define": "This expression is used to mean that things appear to go more slowly if one waits anxiously for it. ",
                "Ex": "There's no point running downstairs for every mail delivery. A watched pot never boils. "
              },
              {
                "Idiom": "Armchair critic",
                "Define": "An armchair critic is a person who knows or pretends to know a lot about something in theory rather than practice. ",
                "Ex": "He is such an armchair critic; he has no experience in the subject but he is ready to give plenty of advice. "
              },
              {
                "Idiom": "Be part of the furniture",
                "Define": "If someone or something is part of the furniture, they have been somewhere so long as to seem an integral part of the place. ",
                "Ex": "She worked for that company for so long that she became part of the furniture."
              },
              {
                "Idiom": "Bring the curtain down",
                "Define": "To bring the curtain down to something means to bring something to an end. ",
                "Ex": "I am asking for a divorce. It's time to bring the curtain down."
              },
              {
                "Idiom": "Bring to the table",
                "Define": "To bring something to the table means to contribute something to a group effort.",
                "Ex": "It is all about you bring to the table. "
              },
              {
                "Idiom": "Call on the carpet",
                "Define": "To reprimand; to censure severely or angrily. ",
                "Ex": "I hope he wouldn't be called on the carpet by the boss. "
              },
              {
                "Idiom": "Cut a rug",
                "Define": "To dance. ",
                "Ex": "The couple impressed everybody when they cut a rug at the party."
              },
              {
                "Idiom": "Darken someone's door",
                "Define": "To darken someone's door means to be an unwelcome visitor.",
                "Ex": "Never darken my door again! "
              },
              {
                "Idiom": "Doormat",
                "Define": "The phrase To be a doormat or to be treated like a doormat describes a weak person who is abused by others and submits to domination. ",
                "Ex": "His colleagues treat him like a doormat. "
              },
              {
                "Idiom": "Draw the curtain on over",
                "Define": "To draw the curtain on or over something means to bring it to an end. ",
                "Ex": "I think it's time for me to draw the curtain on a long career of teaching. "
              },
              {
                "Idiom": "Have a lot on one’s plate",
                "Define": "This idiom is used to mean that one is very busy and have commitments. Another variation of this idiom is have too much on one's plate.   ",
                "Ex": "Alice: Are you coming to the party tonight, Jane Jane: No, I have a lot on my plate right now.    "
              },
              {
                "Idiom": "Have too much on one's plate",
                "Define": "The idiom have too much on one's plate means to be too busy. Another variation of this idiom is have a lot on one's plate.",
                "Ex": "I have too much on my plate right now. I can't help you."
              },
              {
                "Idiom": "In one’s cups",
                "Define": "Drunk; in the act of consuming alcohol liberally. ",
                "Ex": "He couldn't be understood because he was in his cups. "
              },
              {
                "Idiom": "In the oven",
                "Define": "If a woman has one in the oven, it means that she is pregnant. ",
                "Ex": "She probably has one in the oven. "
              },
              {
                "Idiom": "Lie like a rug",
                "Define": "To lie like a rug means to tell lies shamelessly. ",
                "Ex": "She says she didn't kill him, but the detective knows she's lying like a rug."
              },
              {
                "Idiom": "Lift the curtain",
                "Define": "1. To lift the curtain on something means:to start. 2. to make something known or public; disclose.",
                "Ex": "1. It's time to raise the curtain, guys. Let's start working. 2. The company decided to lift the curtain on their new product."
              },
              {
                "Idiom": "Memory like a sieve",
                "Define": "To have a memory like a sieve means to have a very poor memory. ",
                "Ex": "He's got a memory like a sieve "
              },
              {
                "Idiom": "Off the shelf",
                "Define": "Ready made for purchase; in a form that is ready to be used. ",
                "Ex": "It is often cheaper to buy off the shelf goods. "
              },
              {
                "Idiom": "On the table",
                "Define": "Being discussed or considered. ",
                "Ex": "Everybody agreed to leave the plan to build a new school in our town on the table. "
              },
              {
                "Idiom": "Sweep something under the carpet",
                "Define": "(Also sweep something under the rug.) to hide or ignore something. ",
                "Ex": "You've made a terrible mistake. Don't try to sweep it under the carpet! "
              }
            ],
            "General": [
              {
                "Idiom": "All the more",
                "Define": "Even more. ",
                "Ex": "Her family didn't want her to get married to her new boyfriend, but that just made her all the more determined. "
              },
              {
                "Idiom": "Along the lines",
                "Define": "In a general direction or manner. ",
                "Ex": "I was thinking along the lines of a vegetable garden, but I could be persuaded to include some perennials. "
              },
              {
                "Idiom": "Bag of tricks",
                "Define": "A set of techniques and methods. ",
                "Ex": "Why don't you use your bag of tricks to help us solve this problem "
              },
              {
                "Idiom": "Be a barrel of laughs",
                "Define": "Be enjoyable or entertaining. ",
                "Ex": "This movie is a real barrel of laughs. "
              },
              {
                "Idiom": "Behind bars",
                "Define": "In jail or prison. ",
                "Ex": "That guy over there has been behind bars for five years. "
              },
              {
                "Idiom": "Behind closed doors",
                "Define": "In private; in one's private life. ",
                "Ex": "What you do with your partners behind closed doors is none of my business. "
              },
              {
                "Idiom": "Big deal",
                "Define": "Something very important, difficult, or of concern.",
                "Ex": "It's no big deal if you don't finish."
              },
              {
                "Idiom": "Big wheel",
                "Define": "A person with a great deal of power or influence, especially a high-ranking person in an organization.",
                "Ex": "She's a big wheel at IBM."
              },
              {
                "Idiom": "Bite me",
                "Define": "An expression of discontent, aggravation or anger. ",
                "Ex": "Why are you shouting like that  Oh, bite me! "
              },
              {
                "Idiom": "Blessing in desguise",
                "Define": "A blessing in disguise is said when a misfortune has some unexpected benefits. ",
                "Ex": "His failure to pass the exam was a blessing in disguise. This made him realize the importance of hard work."
              },
              {
                "Idiom": "Blow a kiss",
                "Define": "To kiss one's hand, then blow on the hand in a direction towards the recipient. ",
                "Ex": "We haven't yet kissed, but she blew me a kiss as the train pulled out of the station. That meant a lot to me "
              },
              {
                "Idiom": "Call a spade a spade",
                "Define": "To be truthful about something, even if it is rude or unpleasant.",
                "Ex": "Lacy never fears to tell the truth. She calls a spade a spade."
              },
              {
                "Idiom": "Case in point",
                "Define": "An example that illustrates a point ",
                "Ex": "Planning with these toys can be dangerous. For a case in point, look at what happened to our neighbor's child."
              },
              {
                "Idiom": "Chalk something up to inexperience",
                "Define": "To attribute a failure to inexperience and learn from that particular experience. ",
                "Ex": "Chalk it up to inexperience, I guess, but he made a very poor decision. "
              },
              {
                "Idiom": "Come clean",
                "Define": "To be honest and tell the truth.",
                "Ex": "She came clean about what she had done. "
              },
              {
                "Idiom": "Come to terms with",
                "Define": "To gradually accept a sad situation. ",
                "Ex": "I think he will come to terms with the death of his wife. "
              },
              {
                "Idiom": "Come to your attention",
                "Define": "(Also come to your notice) said when you notice something.",
                "Ex": "It has recently come to the workers attention that changes are taking place in the company because of financial problems."
              },
              {
                "Idiom": "Come true",
                "Define": "Said when something happens although it was unlikely that it would.",
                "Ex": "Her dream of buying a new car finally came true."
              },
              {
                "Idiom": "Common as an old shoe",
                "Define": "(Also as common as dirt) low class; unrefined; ill-mannered; uncouth. ",
                "Ex": "That girl is common as an old shoe. "
              },
              {
                "Idiom": "Dance with death",
                "Define": "Try to do something that involves a lot of risks.",
                "Ex": "He danced with death when he tried to negotiate a deal with that dangerous criminal. "
              },
              {
                "Idiom": "Dead loss",
                "Define": "Something described as a dead loss is absolutely unsuccessful or useless (a complete failure) ",
                "Ex": "When it comes to math, my sisiter is a dead loss. "
              },
              {
                "Idiom": "Do the trick ",
                "Define": "Said about something that works.",
                "Ex": "Some lemon juice should do the trick to make this sauce more delicious. "
              },
              {
                "Idiom": "Drop the subject",
                "Define": "To stop discussing a subject. ",
                "Ex": "Please drop the subject. I don't want to discuss it further. "
              },
              {
                "Idiom": "Easy come, easy go",
                "Define": "Said about something which is easily won or obtained and then soon spent or lost. ",
                "Ex": "He lost a large amount of money in poker. But that's gambling; easy come, easy go."
              },
              {
                "Idiom": "Every trick in the book",
                "Define": "Said when you try every possible way to achieve something.",
                "Ex": "She's tried every trick in the book to convince him in vain. "
              },
              {
                "Idiom": "Fall through the cracks",
                "Define": "To be missed; to escape the necessary notice or attention",
                "Ex": "Complete every item, and make sure nothing falls through the cracks. "
              },
              {
                "Idiom": "For all I care",
                "Define": "Used to suggest that you don't care. ",
                "Ex": "You can go to the party alone, for all I care "
              },
              {
                "Idiom": "Gas up",
                "Define": "To fill a vehicle with gasoline. ",
                "Ex": "I have to stop at the next station to gas up. "
              },
              {
                "Idiom": "Get off the track",
                "Define": "To start talking about a different topic, instead of talking about the real one. ",
                "Ex": "Instead of discussing the real reasons for their conflicts, they are getting of the track. "
              },
              {
                "Idiom": "Have an axe to grind",
                "Define": "To have a strong opinion about something. ",
                "Ex": "The members of that association have no political axe to grind; they just want to help the street children."
              },
              {
                "Idiom": "Have one's wires crossed",
                "Define": "(Also get one's wires crossed) to be confused. ",
                "Ex": "You've really got your wires crossed! You don't know what you are talking about. "
              },
              {
                "Idiom": "How come ",
                "Define": "Used to ask how or why.",
                "Ex": "So how come you missed the train  "
              },
              {
                "Idiom": "In the bag",
                "Define": "Certain or extremely likely to occur; assured about the success of somoething. ",
                "Ex": "Don't worry about the final exam. It's in the bag. "
              },
              {
                "Idiom": "In the wake of",
                "Define": "Following, as a result of. ",
                "Ex": "Dominique Strauss-Kahn resigns his position as head of the International Monetary Fund in the wake of sexual assault charges. "
              },
              {
                "Idiom": "In the zone",
                "Define": "Focused ",
                "Ex": "He's doing a good job. He's in the zone! "
              },
              {
                "Idiom": "Jam on the brakes",
                "Define": "To press the brakes suddenly and in a hard way. ",
                "Ex": "I had to jam on the brakes because a kid suddenly appeared from nowhere and crossed the road."
              },
              {
                "Idiom": "Keep somebody in stitches",
                "Define": "To keep somebody laughing hard or amused.",
                "Ex": "The show kept me in stitches the whole time. "
              },
              {
                "Idiom": "Keep tabs on",
                "Define": "To monitor; to keep track of; to watch.",
                "Ex": "If you are careful to keep tabs on your finances, you should be able to stay within a budget. "
              },
              {
                "Idiom": "Kick the bucket",
                "Define": "To die.",
                "Ex": "Sad news! He kicked the bucket. "
              },
              {
                "Idiom": "Know something inside and out",
                "Define": "To know something very thoroughly.",
                "Ex": "He's still new to their system, but he knows databases inside and out and will understand the rest soon. "
              },
              {
                "Idiom": "Magic touch",
                "Define": "A special skill to do something very well. ",
                "Ex": "Her magic touch is so obvious in the decoration of her house. "
              },
              {
                "Idiom": "Magic wand",
                "Define": "A quick and an easy way to solve a problem ",
                "Ex": "The manager warned that he had no magic wand to solve the problem. "
              },
              {
                "Idiom": "Mark my words",
                "Define": "Listen to me; used before a statement one wishes to emphasize. ",
                "Ex": "Mark my words, this boy is going to become a great poet."
              },
              {
                "Idiom": "Middle of the road",
                "Define": "Having a centrist attitude or philosophy; not extreme, especially politically. ",
                "Ex": "A typical middle of the road compromise is to leave the problem as it is. "
              },
              {
                "Idiom": "Mister Right",
                "Define": "A perfect, ideal or suitable mate or husband. ",
                "Ex": "She waited for years and years, hoping someday to find Mister Right. "
              },
              {
                "Idiom": "Never mind",
                "Define": "1. it's not important; 2. do not be concerned (about someone or something, or about doing something)    ",
                "Ex": "1. I’m soory I’ve lost your book. — Never mind, I don't need it anymore. 2. Go and I’ll join you later. Never mind about me."
              },
              {
                "Idiom": "Next to nothing",
                "Define": "Almost; hardly. ",
                "Ex": "Although they paid him next to nothing, he liked the job. "
              },
              {
                "Idiom": "No comment",
                "Define": "An official refusal to relay any further information, as a response to a newspaper reporter's question.",
                "Ex": "The district attorney said, No comment, when the reporter asked if he knew the identity of the criminal."
              },
              {
                "Idiom": "Not half bad",
                "Define": "Pretty good; okay; decent.",
                "Ex": "It was my first attempt at cooking, but I tried it and it was not half bad."
              },
              {
                "Idiom": "Not miss a trick",
                "Define": "Said about someone who is extremely alert. ",
                "Ex": "He was attentive to what the teacher was explaining. He didn't miss a trick. "
              },
              {
                "Idiom": "Nothing to sneeze at",
                "Define": "Not bad; decent; acceptable; worthwhile. ",
                "Ex": "Their music may not be worthy of radio time, but it's nothing to sneeze at. "
              },
              {
                "Idiom": "Odds and ends",
                "Define": "Various often worthless small items. ",
                "Ex": "I have to get rid of a few odds and ends before moving to the new house."
              },
              {
                "Idiom": "Pack a wallop",
                "Define": "(Also pack a punch) to provide energy, power, or excitement. ",
                "Ex": "This drink really packs a wallop. "
              },
              {
                "Idiom": "Pass the buck",
                "Define": "To blame others for something you shoud accept reponsibilty for. ",
                "Ex": "It's not my fault. Don't try to pass the buck! "
              },
              {
                "Idiom": "Pay dearly",
                "Define": "To suffer because of a particlar action.",
                "Ex": "If you don't work hard, you will pay dearly for it. "
              },
              {
                "Idiom": "Pick of the bunch",
                "Define": "(Also the best of the bunch) The best. ",
                "Ex": "The dress she's wearing is the pick of the bunch."
              },
              {
                "Idiom": "Picture paints a thousand words",
                "Define": "(Also a picture is worth a thousand words) A picture will be far more descriptive of something than words can ever be. ",
                "Ex": "Just show him the photos and he will understand. You know a picture paints a thousand words."
              },
              {
                "Idiom": "Right down to",
                "Define": "Considering even minor things or people.",
                "Ex": "We are all concerned with obeying the law, from the minister down to the common man. "
              },
              {
                "Idiom": "Right on the button",
                "Define": "To be exactly right.",
                "Ex": "Her remarks were right on the button."
              },
              {
                "Idiom": "Say your piece",
                "Define": "Tell what you have to say ",
                "Ex": "Stop annoying us. Say your piece and go. "
              },
              {
                "Idiom": "See someone's point",
                "Define": "To understand the meaning that someone is trying to convey. ",
                "Ex": "Yes, I see your point and I think you are absolutley right. "
              },
              {
                "Idiom": "Send up a trial balloon",
                "Define": "To test public opinion and response to something. ",
                "Ex": "They had an excellent idea for the project. They sent up a trial balloon but the response was very negative. "
              },
              {
                "Idiom": "Set the wheels in motion",
                "Define": "To initiate a chain of events necessary to help one achieve a goal (more quickly) ",
                "Ex": "His contribution to the project will surely set the wheels in motion. "
              },
              {
                "Idiom": "Shape up or ship out",
                "Define": "To either improve one's behavior or else be required to leave; to either improve one's performance in an activity or else withdraw from that activity completely. ",
                "Ex": "After his many serious mistakes, the boss warned him that he had to shape up or ship out. "
              },
              {
                "Idiom": "Shift gears",
                "Define": "To change what you are doing in a sudden way. ",
                "Ex": "I'd like to shift gears and start a new job. "
              },
              {
                "Idiom": "Take a dim view of ",
                "Define": "To disapprove of something. ",
                "Ex": "My grandfather takes a dim view of the new law. "
              },
              {
                "Idiom": "Take it on the lam",
                "Define": "To run away. ",
                "Ex": "The criminal had to take it on the lam."
              },
              {
                "Idiom": "Take it or leave it",
                "Define": "Said about an offer when you either accept it or reject it completely. ",
                "Ex": "This is my offer; take it or leave it."
              },
              {
                "Idiom": "The customer is always right.",
                "Define": "In order to make profit, it is necessary for a business to satisfy customers' wishes and make them happy. ",
                "Ex": "Look at that waiter! He always argues with customers. He doesn't know that the customer is always right. "
              },
              {
                "Idiom": "The jury is out",
                "Define": "An outcome or decision is still unknown and awaited.",
                "Ex": "The jury is out as to whether there is life anywhere else in the universe. "
              },
              {
                "Idiom": "To pull the trigger",
                "Define": "1. To fire a gun. 2. To commit to a course of action. ",
                "Ex": "Some traders are too afraid to pull the trigger and just watch the market without ever getting involved."
              },
              {
                "Idiom": "Trick of the trade",
                "Define": "A clever skill related to a profession. ",
                "Ex": "He is so skillful. He learned te trick of the trade from his father. "
              },
              {
                "Idiom": "Under pressure",
                "Define": "To be facing something in a stressful environment due to a pressure or a deadline. ",
                "Ex": "They have been under a lot of pressure recently becaues of the huge work they have been doing. "
              },
              {
                "Idiom": "Under the influence",
                "Define": "Intoxicated, inebriated, or otherwise stupefied by an ingested mind-altering substance, commonly speaking of alcohol : drunk. ",
                "Ex": "He was arrested for driving under the influence. "
              },
              {
                "Idiom": "Wait and see",
                "Define": "To wait to see what will occur after. ",
                "Ex": "The new manager hasn't announced his new plan yet. We'll have to wait and see."
              },
              {
                "Idiom": "Walk of life",
                "Define": "An occupation, role, social class, or lifestyle. ",
                "Ex": "People in this neighbourhood come from different walks of life."
              },
              {
                "Idiom": "Waste breath",
                "Define": "To speak in a manner which is needless or futile; in discussion or argument to make points which are not appreciated or heeded. ",
                "Ex": "Please don't waste your breath asking me ridiculous questions. "
              },
              {
                "Idiom": "Watch this space",
                "Define": "An indication that a development will follow. ",
                "Ex": "He has ambitious plans. Watch this space! "
              },
              {
                "Idiom": "Wax and wane",
                "Define": "To increase and decrease.",
                "Ex": "His love for politics has waxed and waned over the years. "
              },
              {
                "Idiom": "Weep buckets",
                "Define": "(Also cry buckets) To cry a lot. ",
                "Ex": "She cried buckets, because that was such a sad event."
              },
              {
                "Idiom": "X marks the spot.",
                "Define": "Said to show the exact spot. ",
                "Ex": "Would you mind moving that armchair here - X marks the spot."
              },
              {
                "Idiom": "You got me there",
                "Define": "I can't answer your question. ",
                "Ex": "I don't kow what to say. You got me there! "
              }
            ],
            "Health": [
              {
                "Idiom": "A clean bill of health",
                "Define": "Said when you examine someone or something and state that they are healthy, in good condition, or legal. ",
                "Ex": "1. The president was given a clean bill of health by his doctors. 2. The company received a clean bill of health because it fulfilled all the safety requirements.     "
              },
              {
                "Idiom": "Alive and kicking ",
                "Define": "(Also be alive and well) To continue to be well, healthy or successful. ",
                "Ex": "1. Don't worry about your grandfather; he is alive and kicking. 2. Classical music is still alive and kicking among youngsters"
              },
              {
                "Idiom": "As blind as a bat",
                "Define": "If someone is as blind as a bat, they are nearly or completely blind or they are  unwilling to recognize problems or bad things. This is a simile which is based on the erroneous idea that bats cannot see properly. In fact, bats are not blind; they use vision to navigate, especially for long distances. In addition to their ability to see, they use a sophisticated built-in sonar system, called echolocation.",
                "Ex": "1. Without her glasses, she is as blind as a bat. 2. He is as blind as a bat when it comes to his wife's shameful behavior  "
              },
              {
                "Idiom": "Bag of bones",
                "Define": "An extremely thin person. ",
                "Ex": "He's turning into a bag of bones. He lost so much weight. "
              },
              {
                "Idiom": "Be full of beans",
                "Define": "Said about someone who is active, lively, healthy and has a lot of energy and enthusiasm. ",
                "Ex": "He's always full of beans when he goes to work. "
              },
              {
                "Idiom": "Bitter pill to swallow",
                "Define": "(Also swallow a bitter pill) Said about something unpleasant that must be accepted or endured. ",
                "Ex": "After the disappointment and defeat, to declare bankruptcy was a bitter pill to swallow for him. "
              },
              {
                "Idiom": "Black out",
                "Define": "To lose consciousness. ",
                "Ex": "He blacked out when he fell. "
              },
              {
                "Idiom": "Hale and hearty",
                "Define": "In a good health. ",
                "Ex": "In spite of her old age, she looks hale and hearty."
              },
              {
                "Idiom": "In bad shape",
                "Define": "In bad physical condition. ",
                "Ex": "Bill is in bad shape. He needs to exercise regularly and go on a diet. "
              },
              {
                "Idiom": "In the best of health",
                "Define": "Very healthy. ",
                "Ex": "He's in the best health because he exercises regularly and doesn't eat junk food. "
              },
              {
                "Idiom": "Just what the doctor ordered",
                "Define": "The idiom just what the doctor ordered means exactly what is required or wanted.",
                "Ex": "Alan: Orange juice  Greg: Thanks! Orange juice is just what the doctor ordered.       "
              },
              {
                "Idiom": "Nurse someone back to health",
                "Define": "To look after a sick person until he recovers. ",
                "Ex": "He is fortunate to have such a caring wife. She was glad to nurse him back to health. "
              },
              {
                "Idiom": "Picture of (good) health",
                "Define": "In a very healthy condition. ",
                "Ex": "The doctor told him that he is a picture of good health. "
              },
              {
                "Idiom": "Poison pill",
                "Define": "The phrase poison pill refers to  is a type of defensive tactic used by companies against a takeover.",
                "Ex": "Many companies have used poison pill strategies against hostile takeovers."
              },
              {
                "Idiom": "Safe and sound",
                "Define": "Safe and without injury or damage. ",
                "Ex": "The kids returned from the excursion safe and sound."
              },
              {
                "Idiom": "White as a sheet",
                "Define": "(Also as white as a sheet) Said about someone whose face is very pale because of illness, shock or fear.",
                "Ex": "Joe looks as white as sheet. He must be very ill. "
              }
            ],
            "Home": [
              {
                "Idiom": "A roof over your head",
                "Define": "A place to live. ",
                "Ex": "He was so poor that he didn't have a roof over his head. "
              },
              {
                "Idiom": "Charity begins at home",
                "Define": "Charity begins at home is a proverb. It means that,  before deciding to take care of other people,  one's family should be one's foremost concern.  ",
                "Ex": "Take care of your children before volunteering in any association. Charity begins at home. "
              },
              {
                "Idiom": "Close to home",
                "Define": "If something is close to home, it affects you personally.",
                "Ex": "His criticism was a bit too close to home. She couldn't bear the way he talked about her work. "
              },
              {
                "Idiom": "Everything but the kitchen sink",
                "Define": "Almost everything, whether needed or not.",
                "Ex": "She must have brought everything but the kitchen sink along on the trip, and how she lifted her suitcase, I do not know."
              },
              {
                "Idiom": "Go home in a box",
                "Define": "To die and be shipped home. ",
                "Ex": "Those soldiers are too young. It's a pitty that they go home in a box. "
              },
              {
                "Idiom": "Go through the roof",
                "Define": "Become very angry. ",
                "Ex": "She went through the roof when she realized she had lost everything. "
              },
              {
                "Idiom": "Hit a home run",
                "Define": "To be successful. ",
                "Ex": "They hit a home run with their excellent performance in the new play. "
              },
              {
                "Idiom": "Hit the ceiling",
                "Define": "To become very angry and start shouting. ",
                "Ex": "He hit the ceiling when he knew the truth. "
              },
              {
                "Idiom": "Hit the roof",
                "Define": "To become angry and start shouting. ",
                "Ex": "The teacher hit the roof when the student was very late again. "
              },
              {
                "Idiom": "Home away from home",
                "Define": "(Also home from home) A place where you are at ease as if you were at home. ",
                "Ex": "When I used to visit her, it was really a real home away from home."
              },
              {
                "Idiom": "Home free",
                "Define": "To be certain of being successful because you have finished the most difficult part. ",
                "Ex": "Once you hand in the last part of your dissertation, you're home free. "
              },
              {
                "Idiom": "Home truth",
                "Define": "The phrase home truth refers to an unpleasant fact about oneself. It is usually in the plural form: home truths.    ",
                "Ex": "It is high time I told him a few home truths. "
              },
              {
                "Idiom": "Make yourself at home",
                "Define": "If you say to someone make yourself at home, this means that you ask them to consider themselves as if they were in their own homes.",
                "Ex": "Alan: Can I get in  John: Yes please, make yourself at home!        "
              },
              {
                "Idiom": "Man's home is his castle",
                "Define": "This idiom suggest that people are free to do whatever they want to in their own home ",
                "Ex": "He was furious when they told him not to listen to his favorite music in his own home. He told them that a man's home is his castle. "
              },
              {
                "Idiom": "Men make houses, women make homes",
                "Define": "It's often the men who build or acquire houses for their families, but it's usually women who provide the things that make a house into a home. ",
                "Ex": "When her husband bought the house, she took charge of decorating and tidying it up. It's true that men make houses, women make homes. "
              },
              {
                "Idiom": "Roof something over",
                "Define": "To build a roof over something. ",
                "Ex": "After the earthquake they had to roof the shed over. "
              },
              {
                "Idiom": "Sweep something under the rug",
                "Define": "To hide something because it's embarassing. ",
                "Ex": "Because many famous people were involved in the affair, everything was swept under the rug. "
              },
              {
                "Idiom": "The home straight",
                "Define": "The last part of a difficult work. ",
                "Ex": "It was just suc a difficult project to work on, but we are on the home the straight. "
              },
              {
                "Idiom": "The home stretch",
                "Define": "The last part of a difficult work. ",
                "Ex": "We are in the home stretch after a year of hard work. "
              },
              {
                "Idiom": "Wake up on the wrong side of bed",
                "Define": "To feel grumpy, irritable; to be easily annoyed. ",
                "Ex": "She must have woken up on the wrong side of the bed this morning. She didn't stop shouting all day long. "
              }
            ],
            "Law": [
              {
                "Idiom": "Above suspicion",
                "Define": "This phrase is used to describe a person who is honest enough that no one would suspect. The origin of the phrase is attributed to Julius Caesar, who divorced his wife Pompeia, on the grounds of her possible involvement in a public scandal, saying that my wife ought not even to be under suspicion. This gave rise to a proverb, sometimes expressed: Caesar's wife must be above suspicion.",
                "Ex": "That guy is a peaceful man; he is above suspicion. "
              },
              {
                "Idiom": "Above the law",
                "Define": "Not subject to the law, exempt from the laws that apply to everyone else. ",
                "Ex": "Nobody is above the law."
              },
              {
                "Idiom": "Bend the law",
                "Define": "The phrase to bend the law means to cheat a little bit without breaking the law.",
                "Ex": "He just bent the law a little bit to get what he wanted."
              },
              {
                "Idiom": "Case-by-case",
                "Define": "Separate and distinct from others of the same kind. ",
                "Ex": "All applications are scrutinized on a case by case basis. "
              },
              {
                "Idiom": "Hold someone accountable (for something)",
                "Define": "To consider someone responsible for something. ",
                "Ex": "I hold you accountable for whatever happens to my daughter."
              },
              {
                "Idiom": "In the eyes of the law",
                "Define": "Legally. ",
                "Ex": "In the eyes of the law you are not allowed to treat people like that. "
              },
              {
                "Idiom": "Justice is blind",
                "Define": "This expression means that justice is impartial and objective. There is an allusion here to the Greek statue for justice, wearing a blindfold so as not to treat friends differently from strangers, or rich people better than the poor ones. ",
                "Ex": "No matter who you are, you must respect the law. Justice is blind! "
              },
              {
                "Idiom": "Law of the jungle",
                "Define": "This expression means survival of the strongest or the fittest. The origin of the phrase can be traced back to The Jungle Book by Rudyard Kipling. He uses the term to describe an actual set of legal codes used by wolves and other animals in the jungles of India  ",
                "Ex": "Some economists think that capitalism is governed by the law of the jungle."
              },
              {
                "Idiom": "Law unto oneself",
                "Define": "This idiomatic expression describes a person who behaves in an independent way, ignoring rules and what is generally accepted as correct. ",
                "Ex": "Leila is a law unto herself; she doesn't want to abide by the rules."
              },
              {
                "Idiom": "Lay down the law",
                "Define": "Tell people what they should do in a forceful and stern way. ",
                "Ex": "Please don't lay down the law; we know what we have to do. "
              },
              {
                "Idiom": "Necessity knows no law",
                "Define": "Necessity knows no law is a proverb. It means that being desperate and having no means may lead you to do illegal things.",
                "Ex": "He was dealing in illegal drugs because he had to feed four kids. Necessity knows no law. "
              },
              {
                "Idiom": "One's word is law",
                "Define": "The idiom one's word is law means that what someone says must be obeyed.",
                "Ex": "There's no point trying to do things differently. The manager's word is law around here. Just do what he asks you to do."
              },
              {
                "Idiom": "Possession is nine points of the law",
                "Define": "Possession is nine points of the law is a phrase used to suggest that if you really possess something, you will easily claim its ownership than someone who just says it belongs to him or her. The phrase comes from the early English property system, where the right to possession of property was endorsed by the king in the form of nine traditional writs. These writs evolved into the nine original laws defining property ownership, hence the expression possession is nine points in the law.   ",
                "Ex": "The jacket you are wearing is presumed to be yours, unless someone can prove that it is not. Possession is nine points of the law. "
              },
              {
                "Idiom": "Read the riot act",
                "Define": "If you read the riot act to someone, you warn or reprimand them energetically or forcefully. ",
                "Ex": "The principal read the riot act to the trouble makers. "
              },
              {
                "Idiom": "Signed, sealed and delivered",
                "Define": "(Also sealed and delivered) This expression refers to a document or an agreement which has been officially signed and completed satisfactorily.   ",
                "Ex": "John: Hey, have you finalized the purchase of the estate yet  Leila: Yes, I got all the documents... signed, sealed, and delivered!         "
              },
              {
                "Idiom": "The letter of the law",
                "Define": "This idiom is used when one is obeying the literal interpretation of the law, but not the intent or the spirit of those who wrote the law. ",
                "Ex": "Judges mustn't follow the letter of the law, but its spirit."
              },
              {
                "Idiom": "The long arm of the law",
                "Define": "This idiomatic expression refers to the far-reaching power of the authorities or the police. ",
                "Ex": "Don't try to escape! The long arm of the law will catch you wherever you may go."
              },
              {
                "Idiom": "The spirit of the law",
                "Define": "When one obeys the spirit of the law but not the letter, one is doing what the authors of the law intended, though not necessarily adhering to the literal wording. (See also <a href=http://www.myenglishpages.com/site_php_files/random-idiom.php c=719 title=letter of the law>letter of the law</a>) In one of the best known examples, The Merchant of Venice, Shakespeare introduces the quibble as a plot device to save both the spirit and the letter of the law. The moneylender Shylock has made an agreement with Antonio that if he cannot repay a loan he will have a pound of flesh from him. When the debt is not repaid in time Portia at first pleads for mercy in a famous speech: The quality of mercy is not strain'd, It droppeth as the gentle rain from heaven upon the place beneath. It is twice blest: It blesseth him that gives and him that takes. (IV,i,185). When Shylock refuses, she finally saves Antonio by pointing out that Shylock's agreement with him mentioned no blood, and therefore Shylock can have his pound of flesh only if he sheds no blood.",
                "Ex": "A judge who adheres to the spirit of the law is concerned with the intent and purpose of the lawmaker. "
              },
              {
                "Idiom": "Unwritten law",
                "Define": "The phrase unwritten law refers to an accepted rule in spite of its informality.",
                "Ex": "It's an unwritten law to take your hat off during the national anthem. "
              }
            ],
            "Life": [
              {
                "Idiom": "A fact of life",
                "Define": "This idiom is used to refer to something which is unpleasant and which people accept because they cannot change it. ",
                "Ex": "Violence has become a fact of life among teenagers these days. "
              },
              {
                "Idiom": "A life-saver",
                "Define": "This phrase is used to refer to something or someone that saves a person in a difficult situation or critical moment. ",
                "Ex": "Thank you so much for your help. You're a real life-saver! "
              },
              {
                "Idiom": "A new lease of life",
                "Define": "This idiom is used to refer to an occasion when something gives you the chance to become happy or healthy and makes you more energetic than before. ",
                "Ex": "His new job has given him a new lease of life. "
              },
              {
                "Idiom": "All walks of life",
                "Define": "Occupations, roles, social class, or lifestyle. ",
                "Ex": "Those who attended the wedding represented all walks of life. "
              },
              {
                "Idiom": "Between life and death",
                "Define": "This phrase is used to refer to a situation where both living and dying are possible. ",
                "Ex": "After his terrible accident, he was for a long time between life and death. "
              },
              {
                "Idiom": "Charmed life",
                "Define": "The phrase charmed life refers to a life protected as if by magic charms. It describes a person who is very lucky and is strangely unaffected by dangers and difficulties. The phrase was first used by Shakespeare in his play, Macbeth in 1605. Thou losest labor. As easy mayst thou the intrenchant air. With thy keen sword impress as make me bleed. Let fall thy blade on vulnerable crests; I bear a charmèd life, which must not yield. To one of woman born. The two lines:I bear a charmed life, which must not yield. To one of woman bornmean: I lead a charmed life, which can’t be ended by anyone born from a woman.",
                "Ex": "Everybody believes that he leads a charmed life. He was lucky enough to survive after a terrible car accident."
              },
              {
                "Idiom": "Dog's life",
                "Define": "A miserable, unhappy existence. ",
                "Ex": "I have to work everyday from dawn to sunset and come back home to take care of the children. It's really a dog's life. "
              },
              {
                "Idiom": "Fact of life",
                "Define": "Something that cannot be avoided. ",
                "Ex": "It is a pity that drug abuse has become a fact of life in the Olympic Games. "
              },
              {
                "Idiom": "For the life of me",
                "Define": "This idiom is used colloquially to mean if one's (own) life depended on it.",
                "Ex": "I couldn't for the life of me remember where I met her."
              },
              {
                "Idiom": "Get a life",
                "Define": "This is an idiom that indicates that someone's life is boring and pointless and that they should make their life more interesting. ",
                "Ex": "Stop complaining! Get a life. "
              },
              {
                "Idiom": "Give the kiss of life",
                "Define": "To give the kiss of life means to help a person who has stopped breathing by giving them artificial respiration, that is to say, by blowing into their mouth and pressing their chest. ",
                "Ex": "He saved a victim of an accident by giving him the kiss of life. "
              },
              {
                "Idiom": "Larger than life",
                "Define": "Very imposing, renowned, or impressively influential. ",
                "Ex": "He is such a special man; somewhat larger than life. "
              },
              {
                "Idiom": "Low-life",
                "Define": "(Also lowlife) A low-life is a person who is considered morally unacceptable by their community such as thieves, drug dealers, drug users, alcoholics, thugs, prostitutes and pimps. ",
                "Ex": "I saw him with a bunch of lowlifes."
              },
              {
                "Idiom": "Run for one's life",
                "Define": "To run for your life means to run away to save one's life. ",
                "Ex": "A lion escaped from the zoo. Run for your life. "
              },
              {
                "Idiom": "Shelf life",
                "Define": "The length of time something will last. ",
                "Ex": "This medicine has a short shelf life. "
              },
              {
                "Idiom": "Slice of life",
                "Define": "The phrase slice of life refers to a realistic representation of everyday experience in art and entertainment (e.g. a movie, play, book..)",
                "Ex": "The movie is a slice of life about the life of a group of students. "
              },
              {
                "Idiom": "Take someone's life",
                "Define": "To kill someone. ",
                "Ex": "The floods took hundreds of lives. "
              },
              {
                "Idiom": "You can bet your life",
                "Define": "This idiom is used to mean that you are absolutely certain that something is true or will happen. ",
                "Ex": "You can bet your life they'll get married."
              }
            ],
            "Love": [
              {
                "Idiom": "Blind date",
                "Define": "A social meeting where the two people have never met before. ",
                "Ex": "I went on a blind date yesterday but it was a total fiasco."
              },
              {
                "Idiom": "Cupboard love",
                "Define": "The phrase cupboard love refers to affection that is given purely to gain something from someone. The phrase comes from the way in which a cat will give the person who feeds it superficial love. ",
                "Ex": "It was just cupboard love, and what she really wanted was the money she used to get from him. "
              },
              {
                "Idiom": "Fall for someone",
                "Define": "To fall in love with someone. ",
                "Ex": "He fell for her because she's so beautiful. "
              },
              {
                "Idiom": "Fall head over heels for someone",
                "Define": "To be in love with someone very much; hopelessly smitten. ",
                "Ex": "They fell head over heels in love with each other. "
              },
              {
                "Idiom": "Fall in love with someone",
                "Define": "To come to have feelings of love directed at another person or a thing ",
                "Ex": "They fell in love with each other from the moment they saw each other. "
              },
              {
                "Idiom": "Fall out of love",
                "Define": "To stop being in love with someone. ",
                "Ex": "She fell out of love with him when she knew he had been hiding secrets from her."
              },
              {
                "Idiom": "For the love of God",
                "Define": "The phrase for the love of is used to express surprise, exasperation, annoyance, or some similar feelingFor the love of Mike, or for the love of Pete are variations of this phrase. For the sake of... is another way to use this idiom.   ",
                "Ex": "For the love of God, stop shouting! "
              },
              {
                "Idiom": "Go steady with someone",
                "Define": "To date one person regularly and exclusively. ",
                "Ex": "Lisa has been going steady with that guy for a year now."
              },
              {
                "Idiom": "Love at first sight",
                "Define": "An instantaneous attraction ",
                "Ex": "It was love at first sight when we met."
              },
              {
                "Idiom": "Love is blind",
                "Define": "The idiom love is blind means that a person who is in love can see no faults or imperfections in the person who is loved.",
                "Ex": "A: I can't see why Leila likes Tim. He isn't even good-looking.B: Love is blind.  "
              },
              {
                "Idiom": "Love me, love my dog",
                "Define": "The phrase love me, love my dog means that if you love someone, you must be willing to accept everything about them. ",
                "Ex": "Alan decided to get married to Lisa although her brother was a drug dealer. When they started their relationship, she often insisted: love me, love my dog. "
              },
              {
                "Idiom": "Love nest",
                "Define": "A place where a couple can enjoy each other's company. ",
                "Ex": "They rent an apartment which has become their love nest. "
              },
              {
                "Idiom": "Love someone to bits",
                "Define": "The idiom to love someone to bits means to love someone very much.",
                "Ex": "She is the woman I love to bits. "
              },
              {
                "Idiom": "Love-hate relationship ",
                "Define": "A love-hate relationship refers to a relationship that involves both love and hatred. ",
                "Ex": "Nancy has a love-hate relationship with her mother. "
              },
              {
                "Idiom": "Make love, not war",
                "Define": "A hippie anti-war slogan encouraging love and peace. ",
                "Ex": "Why don't you stop fighting! Make love not war! "
              },
              {
                "Idiom": "Match made in heaven",
                "Define": "The phrase a match made in heaven refers to two people, so well-suited to each other that their marriage is likely to be happy and successful. The phrase may also refer to a very successful combination of two people or things.   ",
                "Ex": "As soon as they met, they liked each other and decided they should get married. They were really a match made in heaven."
              },
              {
                "Idiom": "Misery loves company",
                "Define": "The phrase misery loves company means that if someone is miserable, they like others to be miserable too so that they can feel better about themselves.   ",
                "Ex": "I see that you got into a lot of trouble, but since your colleague is in trouble too, that makes you feel better. Misery loves company, doesn't it  "
              },
              {
                "Idiom": "Puppy love",
                "Define": "A childish or youthful infatuation with another person. ",
                "Ex": "As they were still very young nobody took their puppy love seriously."
              },
              {
                "Idiom": "Send love to someone",
                "Define": "The idiom send love to someone refers to an affectionate greeting or message given to someone.",
                "Ex": "Lisa sent her love to all the family."
              },
              {
                "Idiom": "Tough love",
                "Define": "A way of helping someone with compassionate use of stringent disciplinary measures. The aim is to attempt to improve their behavior. ",
                "Ex": "The only way help him get rid of his drug-addiction is to adhere to the principle of tough love."
              }
            ],
            "Men & Women": [
              {
                "Idiom": "A man of action",
                "Define": "A man who is inclined to act first rather than think about things and discuss them. ",
                "Ex": "Bill is really a man of action. Since he arrived at the top of the association, he has done so many things."
              },
              {
                "Idiom": "A man of few words",
                "Define": "(Also a woman of few words) A man who doesn't speak much. A man of action ",
                "Ex": "He is a man of few words. But when he speaks, he makes a lot of sense."
              },
              {
                "Idiom": "A man's man",
                "Define": "The phrase a man's man refers to a man known for traditionally masculine interests and activities. ",
                "Ex": "Alex is what you would call a man's man. "
              },
              {
                "Idiom": "A woman of few words",
                "Define": "(Also a man of few words) A woman who doesn't speak much. A woman of action ",
                "Ex": "She is a woman of few words, but she always gets things done."
              },
              {
                "Idiom": "As one man",
                "Define": "If a group of people do something as one man, they do it unanimously, in complete agreement. ",
                "Ex": "They all rose as one man, supporting the poor family."
              },
              {
                "Idiom": "Company man",
                "Define": "The phrase company man refers to a worker who is more loyal to his employer than to his fellow workers.  ",
                "Ex": "He's never criticized the boss; he has always been a company man. "
              },
              {
                "Idiom": "Every man has his price",
                "Define": "The phrase every man has his price means that everyone can be bribed if you know how much or what to bribe him or her with. ",
                "Ex": "I offered him ten thousand dollars to sign the agreement, but he refused. Just keep trying! Give him more. You know, every man has his price!"
              },
              {
                "Idiom": "Every man jack",
                "Define": "The phrase every man jack means every person without exception.   ",
                "Ex": "All the volunteers contributed their time towards cleaning up the city, every man jack of them."
              },
              {
                "Idiom": "Jangle someones's nerves",
                "Define": "To annoy someone or or make them nervous. ",
                "Ex": "The noise of the kids jangled my nerves."
              },
              {
                "Idiom": "Make a man of someone",
                "Define": "(Also make a man out of someone) To make a young person become more experienced or act like an adult and take responsibility. ",
                "Ex": "A couple of years in a foreign country will make a man of him. "
              },
              {
                "Idiom": "Man cannot live by bread alone",
                "Define": "Used to mean that things like poetry, art, music, etc are necassary for people just as food. ",
                "Ex": "People need to read some poetry! Man cannot live by bread alone. "
              },
              {
                "Idiom": "Man in the street",
                "Define": "(Also, woman in the street) This idiom is used to describe an ordinary person. ",
                "Ex": "Generally speaking, politicians are rarely concerned with the needs and interests of the man in the street. "
              },
              {
                "Idiom": "Man of his word",
                "Define": "The idiom man of his word refers to someone whom you can trust because he keeps his promises and always do what he says.",
                "Ex": "You can count on me. If I say that I'm going to help you, I will do it. I'm a man of my word. "
              },
              {
                "Idiom": "Man of means",
                "Define": "Also a woman of means. The phrase a man of means refers to someone who is very rich  ",
                "Ex": "What a beautiful car! He must be a man of means."
              },
              {
                "Idiom": "Man of straw",
                "Define": "A weak person.",
                "Ex": "When his wife needed his support, he run away and left her facing all the problems all alone. That is why, she called him a man of straw after all."
              },
              {
                "Idiom": "Marked man",
                "Define": "(Also marked woman) The idiom marked man refers to someone who is singled out as a target for vengeance or attack.",
                "Ex": "As a witness to the murder, he knew he was a marked man. "
              },
              {
                "Idiom": "Men in blue",
                "Define": "The phrase men in blue or boys in blue refers to the police or policemen. ",
                "Ex": "The men in blue are chasing the drug dealer. "
              },
              {
                "Idiom": "No use to man or beast",
                "Define": "Said about something or someone that is completely useless. ",
                "Ex": "That old car is no use to man or beast. "
              },
              {
                "Idiom": "Old wives' tale",
                "Define": "A wrong traditional theory or belief, often about health. ",
                "Ex": "The idea of drinking alcohol to relieve you from flu is an old wives' tale."
              },
              {
                "Idiom": "Scarlet woman",
                "Define": "The phrase a scarlet woman refers to a prostitute, an immoral woman, particularly one who commits adultery. The phrase was first used in Revelation 17:5 where a sinful woman was described: 17:4. And the woman was arrayed in purple and scarlet colour, and decked with gold and precious stones and pearls, having a golden cup in her hand full of abominations and filthiness of her fornication: 17:5. And upon her forehead was a name written, MYSTERY, BABYLON THE GREAT, THE MOTHER OF HARLOTS AND ABOMINATIONS OF THE EARTH. [King James Version; the New International Version uses prostitutes instead of harlots].",
                "Ex": "She was the scarlet woman of the town."
              },
              {
                "Idiom": "To a man",
                "Define": "The idiom to a man means without exception.   ",
                "Ex": " All the neighbors were present at the meeting and they all, to a man, agreed to help the poor family."
              },
              {
                "Idiom": "Woman of ill repute",
                "Define": "The idiom a woman of ill repute refers to a prostitute.",
                "Ex": "He started a new relationship with a woman of ill repute."
              }
            ],
            "Money": [
              {
                "Idiom": "A dime's worth",
                "Define": "An insignificant amount ",
                "Ex": "At best, he'll make a dime's worth of difference with his interference in the affair. "
              },
              {
                "Idiom": "A fool and his money are soon parted",
                "Define": "This means that stupid people spend money without thinking about it enough. ",
                "Ex": "John likes his extravagant lifestyle, but then a fool and his money are soon parted. "
              },
              {
                "Idiom": "All that glitters is not gold",
                "Define": "Appearance is sometimes misleading. Things that appear valuable or worthwhile might not be as good as they look. ",
                "Ex": "The house looks beautiful from the outside but the inside part of the house looks terrible; all that glitters is not gold."
              },
              {
                "Idiom": "Bank on",
                "Define": "To expect something or rely on. ",
                "Ex": "Can I bank on your friend's predictions about the stock market "
              },
              {
                "Idiom": "Bet your bottom dollar",
                "Define": "This idiom is used to say that one can be sure about something. ",
                "Ex": "You can bet your bottom dollar. The whole thing will end tragically. "
              },
              {
                "Idiom": "Big bucks",
                "Define": "Lots of money.",
                "Ex": "The new managing director must be making big bucks after his promotion. "
              },
              {
                "Idiom": "Blank cheque",
                "Define": "This phrase is used to mean that one is given an unlimited freedom of action. ",
                "Ex": "The president has been given a blank check to conduct a war against the enemy. "
              },
              {
                "Idiom": "Coining it",
                "Define": "(Also coining money) To be earning a lot of money quickly. ",
                "Ex": "The company has been coining it since the new boss took over. "
              },
              {
                "Idiom": "Coining money",
                "Define": "(Also coining it, mintining it) Earning a lot of money quickly. ",
                "Ex": "The company has been coining money since the new manager took over. "
              },
              {
                "Idiom": "Cost an arm and a leg",
                "Define": "(Also cost a bomb, the earth, a packeta, a small fortune) Extremely expensive.",
                "Ex": "I'd love to buy a Rolls-Royce, but it costs an arm and a leg. "
              },
              {
                "Idiom": "Cut your losses",
                "Define": "This idiom is used to mean that you should do something to avoid losing any more money. ",
                "Ex": "When he felt that his project was failing, he had to sell everything to cut his losses. "
              },
              {
                "Idiom": "Dime a dozen",
                "Define": "Said about anything that is common, inexpensive, and easy to get or available any where. ",
                "Ex": "We don't need someone like him in this company. People with his skills are a dime a dozen these days. "
              },
              {
                "Idiom": "Dollars to donuts",
                "Define": "This idiomatic expression is used to suggest that something is very likely to be true or that one is certain about something. ",
                "Ex": "I'll bet you dollars to donuts she won't accept his marriage proposal."
              },
              {
                "Idiom": "Drop a dime",
                "Define": "To drop a dime means to make a phone call, usually calling the police to inform on or betray someone. ",
                "Ex": "He went out to drop a dime on John."
              },
              {
                "Idiom": "For a song",
                "Define": "Very cheaply.",
                "Ex": "She bought the house for a song."
              },
              {
                "Idiom": "For love nor money",
                "Define": "Said when it is difficult to get something or persuade someone.",
                "Ex": "You can't get help for love nor money these days. "
              },
              {
                "Idiom": "For my money",
                "Define": "In my opinion. ",
                "Ex": "For my money, Bill is the best one to choose as a partner. "
              },
              {
                "Idiom": "Have money to burn",
                "Define": "To be very rich and spend a lot of money on unnecessary things.",
                "Ex": "He seems to have money to burn. He always buys his girlfriend extravagant things."
              },
              {
                "Idiom": "Ill-gotten gains",
                "Define": "Money or other possession gained dishonestly. ",
                "Ex": "All his ill-gotten gains are hidden somewhere in his bedroom."
              },
              {
                "Idiom": "In the money",
                "Define": "Very rich ",
                "Ex": "He's in the money. He's extremely rich. "
              },
              {
                "Idiom": "Licence to print money",
                "Define": "If a company or activity is a licence to print money, it generates a lot of money without much effort. ",
                "Ex": "Advertizing companies are just a licence to print money. "
              },
              {
                "Idiom": "Made of money",
                "Define": "Be rich. ",
                "Ex": "She can't have another car. Her husband is not made of money! "
              },
              {
                "Idiom": "Make a fast buck",
                "Define": "(Also make quick buck) To earn money without much effort. ",
                "Ex": "If you have got any idea of how to make a fast buck, please tell me!"
              },
              {
                "Idiom": "Marry money",
                "Define": "To marry a rich person.",
                "Ex": "She married money and got rich. "
              },
              {
                "Idiom": "Minting it",
                "Define": "(Also minting money) Earning a lot of money quickly. ",
                "Ex": "The restaurant is minting it thanks to the new manager."
              },
              {
                "Idiom": "Minting money",
                "Define": "(Also minting it) Earning a lot of money quickly. ",
                "Ex": "Since the arrival of the new manager, the restaurant is minting money."
              },
              {
                "Idiom": "Money for jam",
                "Define": "(Also be money for old rope) said about a job when it is an easy way of earning money. ",
                "Ex": "Selling ice-cream is money for jam when it is very hot."
              },
              {
                "Idiom": "Money for old rope",
                "Define": "(Also be money for jam) Said about a job when it is an easy way of earning money. ",
                "Ex": "Selling ice-cream is money for old rope when it is very hot. "
              },
              {
                "Idiom": "Money-spinner",
                "Define": "A business or product that makes a lot of money for someone. ",
                "Ex": "Internet commerce is becoming a real money-spinner. "
              },
              {
                "Idiom": "Money talks",
                "Define": "Money talks suggest that with money people can get whatever they want. ",
                "Ex": "She got what she wanted. Well you know money talks! "
              },
              {
                "Idiom": "Not come cheap",
                "Define": "Said about something that is of good quality and is therefore expensive.",
                "Ex": "Fast cars don't come cheap. "
              },
              {
                "Idiom": "Pay your dues",
                "Define": "The phrase pay your dues means to earn respect or a position by a lot of hard work and sacrifice.  ",
                "Ex": "They want me to resign, but everybody knows that I paid my dues to get this position."
              },
              {
                "Idiom": "Pick up the tab",
                "Define": "The idiom to pick up the tab means to pay the bill. Another variation of this idiom is pick up the check.    ",
                "Ex": "John picks up the tab whenever he has dinner with his friends. "
              },
              {
                "Idiom": "Put money on somebody or something",
                "Define": "To bet money or to believe that someone will accomplish something or that something will happen. ",
                "Ex": "He will pass the exam - I'd put money on it. "
              },
              {
                "Idiom": "Quote a price",
                "Define": "State in advance the price for... ",
                "Ex": "The mason quoted a price of 500${'$'} to fix the roof of my house."
              },
              {
                "Idiom": "Rags to riches",
                "Define": "The phrase rags to riches refers to any situation in which a person rises from poverty to wealth.    ",
                "Ex": "He was homeless and went on to create the largest and most successful service company in the country. It's really a rags to riches story. "
              },
              {
                "Idiom": "Strapped for cash",
                "Define": "The idiom strapped for cash to be short of money.   ",
                "Ex": "I'm strapped for cash, can you lend me ten dollars  "
              },
              {
                "Idiom": "Time is money",
                "Define": "A proverb which means that one should not waste time, because one could be using it to earn money. ",
                "Ex": "I have to wake up and go to work - time is money "
              },
              {
                "Idiom": "Turn up like a bad penny",
                "Define": "A person or thing which is unpleasant, disreputable, or otherwise unwanted, especially one which repeatedly appears at inopportune times. ",
                "Ex": "He always turns up like a bad penny. "
              },
              {
                "Idiom": "Worth its weight in gold",
                "Define": "The idiomatic expression worth its weight in gold refers to someone or something that is valuable. When this phrase is applied to a person, the pronoun its is replaced by a personal pronoun, such as his or her.   ",
                "Ex": "The new manager cut down the company's expenses by 30%. She is really worth her weight in gold. "
              }
            ],
            "Music": [
              {
                "Idiom": "All that jazz",
                "Define": "Everything else related to something; and other similar things. ",
                "Ex": "They enjoyed the party: cocktails, dancing, and all that jazz."
              },
              {
                "Idiom": "Blow one's own horn",
                "Define": "(Also toot one's own horn) To brag; to talk boastfully. ",
                "Ex": "Nancy likes to blow her own horn. "
              },
              {
                "Idiom": "Call the tune",
                "Define": "To be the one who controls a situation; to have the most power and authority in a situation. ",
                "Ex": "In any deal you have with banks, it's them who call the tune. "
              },
              {
                "Idiom": "Chin music",
                "Define": "Talk, conversation. ",
                "Ex": "When they meet, there will be plenty of chin music. "
              },
              {
                "Idiom": "Clean as a whistle",
                "Define": "If someone is as clean as a whistle they are perfectly clean. ",
                "Ex": "She's clean as a whistle."
              },
              {
                "Idiom": "Draw in one's horns",
                "Define": "(Also pull in one's horns) To become less impassioned, aggressive, or argumentative; to back down from a fight; to yield or capitulate. ",
                "Ex": "He wanted to fight again but we managed to calm him down and get him to draw in his horns. "
              },
              {
                "Idiom": "Fine-tune",
                "Define": "To make small adjustments to something until optimization is achieved ",
                "Ex": "They need to fine-tune their plan before they start the project. "
              },
              {
                "Idiom": "Have to face the music",
                "Define": "Accept or face the unpleasant consequences of one's actions. ",
                "Ex": "Leila didn't manage to finish the job on time and had to face the music."
              },
              {
                "Idiom": "It takes two to tango",
                "Define": "The expression it takes two to tango means that for something to work properly the cooperation of both parties is needed. Tango is a dance originating in Buenos Aires, Argentina. The phrase originated in a song, Takes Two to Tango, which was written and composed in 1952 by Al Hoffman and Dick Manning.   ",
                "Ex": "For the success of the negotiations, both companies should make some concessions; it takes two to tango, you know."
              },
              {
                "Idiom": "Lock horns",
                "Define": "To come into conflict. ",
                "Ex": "I don't want to lock horns with you. Let's settle this peacefully. "
              },
              {
                "Idiom": "Make chin music",
                "Define": "Talk or chatter. ",
                "Ex": "The boss was furious because he found them sitting there making chin music instead of doing the job."
              },
              {
                "Idiom": "Music to someone's ears",
                "Define": "Some good news; a spoken expression or a sound which is pleasing; a welcome remark or information. ",
                "Ex": "The kind flattering way he used to talk to her was music to her ears. "
              },
              {
                "Idiom": "On the horns of a dilemma",
                "Define": "To face a choice between two equally undesirable alternatives. ",
                "Ex": "I found myself on the horns of dilemma and I didn't know which direction to choose."
              },
              {
                "Idiom": "Pull in one's horns",
                "Define": "(Also draw in one's horn) To become less impassioned, aggressive, or argumentative; to back down from a fight; to yield or capitulate. ",
                "Ex": "They stopped making investments. They pulled in their horns."
              },
              {
                "Idiom": "Stop the music",
                "Define": "Stop everything. ",
                "Ex": "A: (Entering a room full of people doing various things) Stop the music! B: What  A: I have an important announcement!      "
              },
              {
                "Idiom": "Strike a chord",
                "Define": "If something strikes a chord with you, it reminds you of something, it seems familiar to you or you are interested in it. ",
                "Ex": "That woman struck a chord with me. It seems to me that I had seen her before. "
              },
              {
                "Idiom": "Toot one's own horn",
                "Define": "(Also blow one's own horn) To boast; to brag ",
                "Ex": "She really likes to toot her own horn. "
              },
              {
                "Idiom": "Who pays the piper calls the tune",
                "Define": "One who pays for something controls it.",
                "Ex": "If he pays for everything, he will have power over us all. You know, who pays the piper calls the tune "
              }
            ],
            "Names": [
              {
                "Idiom": "Before you can say Jack Robinson",
                "Define": "Very quickliy. ",
                "Ex": "He can do difficult mathemaical operations before you can say Jack Robinson. "
              },
              {
                "Idiom": "Benjamin of the family",
                "Define": "This regers to the youngest child of the family. ",
                "Ex": "Bill is the benjamin of the family. "
              },
              {
                "Idiom": "Bob’s your uncle",
                "Define": "Said to mean, No problem, the solution is simple, there you have it. (appended to the end of a description of how to achieve something).",
                "Ex": "You want to go to the stadium  Go straight on until you reach the park, take the first left and Bob’s your uncle!"
              },
              {
                "Idiom": "By the name of",
                "Define": "Called. ",
                "Ex": "I met a doctor by the name of John. "
              },
              {
                "Idiom": "Call someone names",
                "Define": "To call someone by unpleasant, abusive or insulting names. ",
                "Ex": "Because he called his teacher names, Bill was punished. "
              },
              {
                "Idiom": "Every Tom, Dick and Harry",
                "Define": "Said about something that is common knowledge to everybody. ",
                "Ex": "Every Tom, Dick and Harry knows what happened. "
              },
              {
                "Idiom": "For Pete's sake",
                "Define": "The phrase for Pete's sake is used to expresses frustration, exasperation, annoyance. The phrase is a variant of for Christ's sake, for God's sake. Pete refers perhaps to Saint Peter",
                "Ex": "For Pete's sake, turn off the TV! I need some rest."
              },
              {
                "Idiom": "Freudian slip",
                "Define": "The phrase Freudian slip (also called parapraxis) refers to a mistake in speech that shows what the speaker is truly thinking.",
                "Ex": "Jane: He is such a bighead. Have you heard what he has just said "
              },
              {
                "Idiom": "Go by the name",
                "Define": "The phrase go by the name of means to be known by a specific name, a name that is not your real name.",
                "Ex": "She goes by the name of Lisa."
              },
              {
                "Idiom": "I can't put a name to someone",
                "Define": "Said when you can't remmeber someone's name. ",
                "Ex": "She was my classmate. I remember her face but I can't put a name to her. "
              },
              {
                "Idiom": "In name only",
                "Define": "(Also, only in name) The phrase in name only means nominally, not essentially.",
                "Ex": "They were married only in name; the fact is that they lived in different countries. "
              },
              {
                "Idiom": "It has someone's name on it",
                "Define": "Said about somthing that belongs to someone or meant for someone. ",
                "Ex": "This piece of cake has my name on it. "
              },
              {
                "Idiom": "Jack-of-all-trades",
                "Define": "Said about someone who is able to do many things.",
                "Ex": "He can do many jobs; he's really a jack-of-all-trades "
              },
              {
                "Idiom": "Jekyll and Hyde",
                "Define": "Jekyll and Hyde refers to someone having a dual personality,  one side of which is good and the other evil. The origin of the phrase comes from Robert Louis Stevenson's The Strange Case of Dr. Jekyll and Mr. Hyde (1886).",
                "Ex": "She's a real Jekyll and Hyde. You never know when she will become unpleasant. "
              },
              {
                "Idiom": "Joe Bloggs",
                "Define": "An average typical man.",
                "Ex": "This car is very expensive and is not the sort of thing that Joe Bloggs would buy. "
              },
              {
                "Idiom": "John Hancock",
                "Define": "A person's signature.",
                "Ex": "Put your John Hancock at the bottom of the page. "
              },
              {
                "Idiom": "Name is mud",
                "Define": "If someone's name is mud they are in trouble, disgraced, or discredited. The idiom's origin is said to refer to Samuel Alexander Mudd (December 20, 1833 – January 10, 1883) who was an American physician, imprisoned for conspiring with John Wilkes Booth in the assassination of U.S. President Abraham Lincoln. However, according to an online etymology dictionary, this phrase has its earliest known recorded instance in 1823, ten years before Mudd's birth, and is based on an obsolete sense of the word mud meaning a stupid twaddling fellow.",
                "Ex": "If she doesn't prove her innocence, her name will be mud. "
              },
              {
                "Idiom": "Nervous Nellie",
                "Define": "A nervous Nellie refers to someone whose personality and usual behavior are characterized by worry, insecurity, and timidity. ",
                "Ex": "He is a nervous Nellie; He can't make a decision without the approval of wife."
              },
              {
                "Idiom": "Not be short of a bob or two",
                "Define": "The phrase not be short of a bob or two means to have a lot of money.",
                "Ex": "Her husband is not short of a bob or two. "
              },
              {
                "Idiom": "Patience of Job",
                "Define": "To have the patience of Job means to have a great amount of patience. The idiom has a religious origin. Job was considered a prophet in the Abrahamic Religions: Islam, Christianity and Judaism. He suffered from horrendous disasters that took away all that he held dear, including his offspring, his health, and his property.",
                "Ex": "In spite of their extreme poverty, they managed to raise ten children. They really have the patience of Job."
              },
              {
                "Idiom": "Walter Mitty",
                "Define": "A person, generally quite ordinary with unexceptional qualities, who is prone to fantastic daydreaming of personal triumphs. This term comes from James Thurber's short story, The Secret Life of Walter Mitty (1939), describing a meek, mild man with a vivid fantasy life. The character's name has come into more general use to refer to an ineffectual dreamer. ",
                "Ex": "If you are a Walter Mitty with Everest dreams, you need to bear in mind that when things go wrong up there, you may lose your life. "
              },
              {
                "Idiom": "You name it",
                "Define": "The phrase you name it means anything you say or choose or whatever you can think of. ",
                "Ex": "What would you like to eat  Fish, chicken, pizza  You name it, we've got everything here."
              }
            ],
            "Nature": [
              {
                "Idiom": "A breath of fresh air",
                "Define": "Said about a new, fresh, and imaginative approach, a change that feels good.",
                "Ex": "The president says that the country needs a breath of fresh air. "
              },
              {
                "Idiom": "Add fuel to the fire",
                "Define": "(Also add fuel to the flames) To make a problem worse; to say or do something that makes a bad situation worse. ",
                "Ex": "Don't add fuel to the fire by laughing at him. He is furious about what you have already done."
              },
              {
                "Idiom": "Be dead in the water",
                "Define": "Said when something has no chance of succeeding or of making any progress.",
                "Ex": "Our projects will be dead in the water if we don't have a good plan. "
              },
              {
                "Idiom": "Be dead to the world",
                "Define": "To be sleeping.",
                "Ex": "I think she has woken up. She was dead to the world ten minutes ago. "
              },
              {
                "Idiom": "Be in deep water",
                "Define": "To be in serious trouble.",
                "Ex": "The government is in deep water because of its plans for tax increases. "
              },
              {
                "Idiom": "Be in hot water",
                "Define": "To be in a difficult situation. ",
                "Ex": "He was in hot water because of his speech about racism."
              },
              {
                "Idiom": "Be in the land of the living",
                "Define": "Be awake or to be alive. ",
                "Ex": "He was working all night long. I don't think he'll be in the land of the living before noon. I haven't seen him for ages. I'm surprised to find him stil in the land of the living. "
              },
              {
                "Idiom": "Beat around the bush",
                "Define": "To treat a topic, without mentioning its main points, often intentionally, because the topic is difficult or unpleasant.",
                "Ex": "Stop beating around the bush and tell me what the the problem is! "
              },
              {
                "Idiom": "Calm before the storm",
                "Define": "The calm before the storm is an unusual or false quiet period before a period of upheaval. ",
                "Ex": "The negotiation between the two parties may be peaceful now. But don't be misled! This is only the calm before the storm. "
              },
              {
                "Idiom": "Castles in the air",
                "Define": "Plans that are unlikely to happen. ",
                "Ex": "Before you start building castles in the air, just think how much all this is likely to cost."
              },
              {
                "Idiom": "Come into bloom",
                "Define": "To blossom, to start to produce flowers.",
                "Ex": "Look at the roses are just coming into bloom."
              },
              {
                "Idiom": "Come under fire",
                "Define": "To be subject of criticism.",
                "Ex": "The president has come under fire for his decision to postpone the elections."
              },
              {
                "Idiom": "Draw the shortest straw",
                "Define": "To be selected to do an undesirable task (by drawing the shortest straw or otherwise).",
                "Ex": "I drew the short straw and got stuck doing the whole project alone. "
              },
              {
                "Idiom": "Drop in the bucket",
                "Define": "Something so strong that it doesn't count or doesn't have any importance or significance. ",
                "Ex": "His contribution was just a drop in the bucket compared to the rest of the team. "
              },
              {
                "Idiom": "Fan the flames",
                "Define": "To make a bad feeling or situation become worse or more intense. ",
                "Ex": "His racial declarations fanned the flames of the ethinc war."
              },
              {
                "Idiom": "Gain ground",
                "Define": "To become popular, to make progress, to advance. ",
                "Ex": "The new product gained ground in a very short time."
              },
              {
                "Idiom": "Get into deep water",
                "Define": "To be in trouble.",
                "Ex": "He got into deep water when he joined that gang."
              },
              {
                "Idiom": "In the air",
                "Define": "Said about something that is happening or about to happen.",
                "Ex": "Everybody in the company know that change is in the air. "
              },
              {
                "Idiom": "Make a mountain out of a molehill",
                "Define": "To exagerate the severity of a situation; to make a lot of fuss about nothing. ",
                "Ex": "You're making a mountain out of a molehill. You didn't mean to hurt her. "
              },
              {
                "Idiom": "Make waves",
                "Define": "To cause trouble.",
                "Ex": "Please don't make waves. We're trying to settle all our problems. "
              },
              {
                "Idiom": "Not hold water",
                "Define": "Said when an explanation, a reason or an argument is not sound, strong or logical. ",
                "Ex": "Her reasons just didn't hold water. "
              },
              {
                "Idiom": "Oceans of ",
                "Define": "A large amount of something. ",
                "Ex": "Oceans of guests were at the party. "
              },
              {
                "Idiom": "Of the first water",
                "Define": "Of the best. ",
                "Ex": "She is of the first water, a fine lady indeed. "
              },
              {
                "Idiom": "Rain or shine",
                "Define": "(Also come rain or shine) Whatever happens; no matter whether it is rainy or sunny. ",
                "Ex": "I'll be on time, rain or shine."
              },
              {
                "Idiom": "Root and branch",
                "Define": "Completely. ",
                "Ex": "They have reorganized their business root and branch. "
              },
              {
                "Idiom": "Sail against the wind",
                "Define": "To work to achieve something that is difficult because most people would oppose it. ",
                "Ex": "The journalist is sailing against the wind in his attempt to change people's negative attitude towards that politician."
              },
              {
                "Idiom": "Sail close to the wind",
                "Define": "When you sail close to the wind you act just within the limits of what is legal or acceptable.",
                "Ex": "His business is doing well although he sometimes sail close to the wind."
              },
              {
                "Idiom": "Salt of the earth",
                "Define": "Said about somone who is honest and good. ",
                "Ex": "He is the salt of the earth. He always helps the poor."
              },
              {
                "Idiom": "Sell ice to Eskimos",
                "Define": "To persuade people to go against their best interests or to accept something unnecessary or preposterous. ",
                "Ex": "He's such a smooth talker, he could sell ice to Eskimos. "
              },
              {
                "Idiom": "The chill wind of something",
                "Define": "Problems, trouble.",
                "Ex": "World economies are facing the chill wind of the recession. "
              },
              {
                "Idiom": "Under a cloud ",
                "Define": "To be suspected of something. ",
                "Ex": "After the murder of the kid, all the relatives were under a cloud of suspicion. "
              },
              {
                "Idiom": "Under the sun",
                "Define": "In existance. ",
                "Ex": "If you want to spend your summer holiday, this is the most beautiful place under the sun."
              },
              {
                "Idiom": "Up in the air",
                "Define": "Uncertain, unsettled.",
                "Ex": "The future of the company is still up in the air. "
              },
              {
                "Idiom": "Vale of tears",
                "Define": "The world considered as sad and harsh. ",
                "Ex": "His grandfather left this vale of tears yesterday. "
              },
              {
                "Idiom": "Vanish into the air",
                "Define": "To disappear. ",
                "Ex": "The money just vanished into the air. I can't find it anywhere. "
              },
              {
                "Idiom": "Walk on air",
                "Define": "Very excited or happy. ",
                "Ex": "He was walking on air after he passed the exam. "
              },
              {
                "Idiom": "When it rains, it pours",
                "Define": "(Also, it never rains but it pours) Said when bad things occur in large numbers. ",
                "Ex": "First, he had a terrible accident. Then, his wife had a heart attack. Really when it rains, it pours. "
              }
            ],
            "Numbers": [
              {
                "Idiom": "101",
                "Define": "In the American educational system 101 indicates an introductory course, often with no prerequisites. The meaning has been extended to include any introduction to the basics of anything.",
                "Ex": "You can find healthy recipes in any 101 cook book. "
              },
              {
                "Idiom": "A number cruncher",
                "Define": "1. A number cruncher refers to someone whose job is to work with numbers and mathematics. 2. It may also refer to a computer that is able to solve complicated problems of mathematics. ",
                "Ex": "1. He's a number cruncher. He works for a big firm of accountants.2. Number crunchers are used on election night to try and forecast the result.       "
              },
              {
                "Idiom": "At sixes and sevens",
                "Define": "This idiom is used to describe a state of confusion or disarray. William Shakespeare uses a similar phrase in Richard II: But time will not permit: all is uneven, and every thing is left at six and seven.   ",
                "Ex": "John is at sixes and sevens after the death of his wife."
              },
              {
                "Idiom": "Be on cloud nine",
                "Define": "Feel extreme happiness or elation ",
                "Ex": "She was on cloud nine when he proposed to marry her. "
              },
              {
                "Idiom": "By the numbers",
                "Define": "If you do something by the numbers, you are doing it in a strict, mechanical way, without using your imagination or creativity. ",
                "Ex": "His work is done by the numbers. There is nothing original about it! "
              },
              {
                "Idiom": "Dressed up to the nines",
                "Define": "When someone is dressed up to the nines, they are wearing fashionable or formal clothes for a special occasion. ",
                "Ex": "They were invited to a wedding. That's why, they were dressed up to the nines. "
              },
              {
                "Idiom": "Feel like a million",
                "Define": "(Also feel like a million bucks, feel like a million dollars) To feel like a million means to feel well and healthy, both physically and mentally.   ",
                "Ex": "It is a wonderful day! I feel like a million dollars. "
              },
              {
                "Idiom": "Give me five",
                "Define": "If you say give me five, you want someone to slap your open hand as a greeting or to show joy. ",
                "Ex": "Give me five! shouted Alan after he scored a goal. "
              },
              {
                "Idiom": "Nine times out of ten",
                "Define": "Almost always. ",
                "Ex": "In this country, nine times out of ten trains come late."
              },
              {
                "Idiom": "Nine to five",
                "Define": "Said about a job with normal daytime hours, a job that begins at nine o'clock in the morning and finishes at five. ",
                "Ex": "She's tired of working nine to five. "
              },
              {
                "Idiom": "On all fours",
                "Define": "On all fours mean on one's hands and knees. ",
                "Ex": "He was on all fours, with his daughter on his back. "
              },
              {
                "Idiom": "Once bitten, twice shy",
                "Define": "If someone is said to be once bitten, twice shy, it means that once someone was hurt by something or someone, they will be afraid to try it again. ",
                "Ex": "Since Leila broke up with her boyfriend, she has become very cautious about starting any new relationship. Once bitten, twice shy, you know!"
              },
              {
                "Idiom": "One-off",
                "Define": "Occurring once; one-time. ",
                "Ex": "It is a one-off event. "
              },
              {
                "Idiom": "Public enemy number one",
                "Define": "The idiom public enemy number one refers someone or something that people hate.",
                "Ex": "That terrorist is considered public enemy number one. "
              },
              {
                "Idiom": "Pull the other one",
                "Define": "Used to tell someone that you don't believe what they have just said.",
                "Ex": "Sue, writing poems  Pull the other one - she can't even write a correct sentence! "
              },
              {
                "Idiom": "Quick one",
                "Define": "To have a drink before going somewhere. ",
                "Ex": "Let's have a quick one before we go to work. "
              },
              {
                "Idiom": "Safety in numbers",
                "Define": "safety in numbers is the hypothesis that, by being part of a large physical group or mass, an individual is proportionally less likely to be the victim of a mishap, accident, attack, or other bad event. ",
                "Ex": "Nobody went sightseeing alone, knowing that there was safety in numbers. "
              },
              {
                "Idiom": "Take forty winks",
                "Define": "The idiom take forty winks means to take a nap for a short period of time.",
                "Ex": "I'll just take forty winks before getting ready for work."
              },
              {
                "Idiom": "Take the fifth",
                "Define": "To decline to answer, especially on grounds that it might be incriminating. The origin of the phrase dates back to the Fifth Amendment in the Bill of Rights, which says that a person can't be compelled in any criminal case to be a witness against himself.",
                "Ex": "If you ask me who stole the wallet, I will simply take the fifth. "
              },
              {
                "Idiom": "Talk nineteen to the dozen",
                "Define": "To speak very quickly. ",
                "Ex": "I couldn't understand what he was saying because he was talking nineteen to the dozen. "
              },
              {
                "Idiom": "That makes two of us",
                "Define": "When you use the phrase that makes two of us you mean that the same is true for you. ",
                "Ex": "Jane: I just bought a new car. Anna: That makes two of us! "
              },
              {
                "Idiom": "The third degree",
                "Define": "Give someone or get the third degree designates a close interrogation. The use of the phrase is derived from the brutal form of police interrogation of the same name, well-known in the American crime fiction. The origin of the phrase may refer to the third degree of Freemasonry and the rigorous procedures to advance to that level.   ",
                "Ex": "I don't know why you always give me the third degree every time I hang out with my friends. "
              }
            ],
            "Parts of the body": [
              {
                "Idiom": "Achilles heel",
                "Define": "Said about a strong situation which contains an element of vulnerability. ",
                "Ex": "Journalists considered that minister as the government's Achilles heel."
              },
              {
                "Idiom": "An arm and a leg",
                "Define": "A lot of money.",
                "Ex": "These glasses cost me an arm and a leg. "
              },
              {
                "Idiom": "An eye for an eye",
                "Define": "(Also, an eye for an eye and a tooth for a tooth.) Said to suggest that punishment should equal the crime. At the root of this principle is that one of the purposes of the law is to provide equitable retribution for an offended party. ",
                "Ex": "In some countries, justice operates on the principle of an eye for an eye. That is, if you kill someone, you desrve to die. "
              },
              {
                "Idiom": "Are your ears burning ",
                "Define": "Said about someone who was not present but was the topic of discussion.",
                "Ex": "We were just talking about you. Are your ears burning  "
              },
              {
                "Idiom": "At your mother's knee",
                "Define": "Said about something that you learned when you were a child.",
                "Ex": "She learned to sing at her mother's knee. "
              },
              {
                "Idiom": "Be glad to see the back of someone",
                "Define": "To be happy to get rid of someone; to be happy because someone has left. ",
                "Ex": "The young man was glad to see the back of his father-in-law after he had stayed for a month."
              },
              {
                "Idiom": "Behind one's back",
                "Define": "In one's absence ",
                "Ex": "He was talking nonsense on my back when I arrived. "
              },
              {
                "Idiom": "Big mouth",
                "Define": "Said about someone who tend to say things which are meant to be kept secret.",
                "Ex": "He is such a big mouth. He told them every thing."
              },
              {
                "Idiom": "Bite your lip",
                "Define": "To make an effort not to react to something. ",
                "Ex": "He didn't like the management of the business but he had to bite his lip."
              },
              {
                "Idiom": "Bring to knees",
                "Define": "To destroy or defeat someone or something. ",
                "Ex": "Sanctions were imposed in an attempt to bring the country to its knees."
              },
              {
                "Idiom": "Curl your lip",
                "Define": "An upward movement of the side of the mouth to show dislike and disrespect. ",
                "Ex": "He asked her not curl her lip at him."
              },
              {
                "Idiom": "Dip your toe in the water",
                "Define": "(Also dip a toe in the water) Said when you start something carefully because you are not sure whether it will work or not.",
                "Ex": "I am doing some volunteer work at the new school to try and dip my toe in the water of working in the education field "
              },
              {
                "Idiom": "Drag one's feet",
                "Define": "To procrastinate, put off; to dawdle, avoid, or make progress slowly and reluctantly.",
                "Ex": "He's been dragging his feet about doing his homework."
              },
              {
                "Idiom": "Easy on the ear",
                "Define": "Something (music, voice...) pleasant to listen to.",
                "Ex": "His music is easy on the ear. "
              },
              {
                "Idiom": "Easy on the eye",
                "Define": "Attractive, pleasant to look at.",
                "Ex": "Her paintings are easy on the eye. "
              },
              {
                "Idiom": "Face the music",
                "Define": "Said when someone accepts to confront the unpleasant consequences of one's actions. ",
                "Ex": "After failing to manage the crisis, the manager had to face the music."
              },
              {
                "Idiom": "Fall on deaf ears",
                "Define": "Of a request, complaint, etc, to be ignored.",
                "Ex": "Every time I ask him to do something for me, it falls on deaf ears."
              },
              {
                "Idiom": "Feast for the eyes",
                "Define": "Visually pleasing sight.",
                "Ex": "Look at that painting. It's really a feast for the eyes."
              },
              {
                "Idiom": "Get in someone's hair",
                "Define": "Annoy someone. ",
                "Ex": "I know that the children get in your hair, but you should try not to let it upset you so much. "
              },
              {
                "Idiom": "Get something off your chest",
                "Define": "To reveal something that is worrying you or making you feel guilty in order to feel relieved. ",
                "Ex": "She felt relieved when she got it off her chest. She had felt guilty for years. "
              },
              {
                "Idiom": "Give somebody a leg up",
                "Define": "To help someone to achieve something, especially at work.",
                "Ex": "They agreed to give her a leg up. "
              },
              {
                "Idiom": "Give your eye teeth for something",
                "Define": "Said when you want to have or do something very much. ",
                "Ex": "She'd give her eye teeth for a straight blond hair. "
              },
              {
                "Idiom": "Go in one ear and out the other",
                "Define": "Said about something which is heard and then quickly forgotten. ",
                "Ex": "Stop talking to him. Whatever you say to him just goes in one ear and out the other. "
              },
              {
                "Idiom": "Have a leg up on somebody",
                "Define": "To have an advantage over someone.",
                "Ex": "She probably has a leg up on the other students because she is more studious. "
              },
              {
                "Idiom": "Have big ears",
                "Define": "To be nosy and listen to other people's private conversations. ",
                "Ex": "Speak quietly. Nancy has big ears you."
              },
              {
                "Idiom": "Head over heels",
                "Define": "Completely in love. ",
                "Ex": "They fell head over heels for one another at the very moment they met."
              },
              {
                "Idiom": "Hit the nail on the head",
                "Define": "Said to describe exactly a situation or a problem.",
                "Ex": "I think you hit the nail on the head when you said that the Smiths lack a sense of cooperation in their family. "
              },
              {
                "Idiom": "In a body",
                "Define": "Said when a group of people do something together.",
                "Ex": "The workers went in a body to the boss to ask for higher wages. "
              },
              {
                "Idiom": "In the blink of an eye",
                "Define": "Very quickly.",
                "Ex": "He disappeared in the blink of an eye."
              },
              {
                "Idiom": "Keep a civil tongue",
                "Define": "(Also keep a civil tongue in one's head) To speak politely. ",
                "Ex": "Please don't talk like that to each other. Keep a civil tongue!"
              },
              {
                "Idiom": "Keep an eye on",
                "Define": "Keep an eye on something or someone means to watch or look after something or someone.",
                "Ex": "Please keep your eye on my son while I go to the toilet. "
              },
              {
                "Idiom": "Keep body and soul together",
                "Define": "To be able to pay for your food, clothing and somewhere to live.",
                "Ex": "He earns barely enough to keep body and soul together. "
              },
              {
                "Idiom": "Keep one's eye open",
                "Define": "(Also keep one's eye peeled or skinned) to remain alert and watchful. ",
                "Ex": "Please keep your eyes peeled for the children."
              },
              {
                "Idiom": "Keep one's eyes peeled",
                "Define": "To be on the alert, to be watchful ",
                "Ex": "I will keep my eyes peeled for your call. "
              },
              {
                "Idiom": "Keep your head above water",
                "Define": "Be just able to make enough money to survive.",
                "Ex": "After his financial problems, he can hardly keep his head above water. "
              },
              {
                "Idiom": "Knit one's brow",
                "Define": "To frown or look worried, angry or puzzled. ",
                "Ex": "She knitted her brows as she listened to the strange story."
              },
              {
                "Idiom": "Learn by heart",
                "Define": "(Also learn by heart) To memorize something. ",
                "Ex": "She learned the poem by heart."
              },
              {
                "Idiom": "Make a clean breast of",
                "Define": "To tell the truth; to confess. ",
                "Ex": "After he had lied about the stolen money, he was urged to make a clean breast of it."
              },
              {
                "Idiom": "Make somebody's mouth water",
                "Define": "When the smell or sight of food is extremely good it makes your mouth water. ",
                "Ex": "The smell of that roast chicken is making my mouth water. "
              },
              {
                "Idiom": "Not have a leg to stand on",
                "Define": "Not have a sound justification, a firm foundation of facts to prove something.",
                "Ex": "After the police caught him, he didn't have a leg to stand on to prove his innocence. "
              },
              {
                "Idiom": "On the face of it",
                "Define": "On the surface. ",
                "Ex": "On the face of it, she seems innocent. But when the police investigated her case, they discovered that she was guilty. "
              },
              {
                "Idiom": "Over my dead body",
                "Define": "If you say something will happen over your dead body, you mean that you will not allow it to happen.",
                "Ex": "He says he will become our boss. Over my dead body! "
              },
              {
                "Idiom": "Pain in the neck",
                "Define": "An annoyance. ",
                "Ex": "The teacher's last assignment is really a pain in the neck."
              },
              {
                "Idiom": "Pay lip service",
                "Define": "An insincere loyalty, respect, or support for something",
                "Ex": "He says he supports the idea of voluntary work, but in fact he's just paying lip service. "
              },
              {
                "Idiom": "Pull somebody's leg",
                "Define": "To tease or fool someone when trying to convince them to believe something which is not true as a joke.",
                "Ex": "Are you pulling my leg? Is it really your house?  "
              },
              {
                "Idiom": "Pull the other leg ",
                "Define": "Used when you do not believe what someone has just said.",
                "Ex": "Sue, writing poems. Pull the other leg - she can't even write a correct sentence! "
              },
              {
                "Idiom": "Put on a brave face",
                "Define": "To pretend that a problem doesn't bother you. ",
                "Ex": "He looks calm, but I suspect he's just putting on a brave face."
              },
              {
                "Idiom": "Put one's mind to it",
                "Define": "To apply oneself; to exert a directed effort.",
                "Ex": "You can do anything, if you put your mind to it."
              },
              {
                "Idiom": "Put one's shoulder to the wheel",
                "Define": "To start hard work; to begin to toil. ",
                "Ex": "Just put your shoulder to the wheel. If you keep working hard, you’ll be successful one day! "
              },
              {
                "Idiom": "Put words in somebody's mouth",
                "Define": "To attribute to somebody or something he or she did not say; to claim inaccurately that somebody said or intended something.",
                "Ex": "I hope I'm not putting words in your mouth. Did you just tell me to go home early? "
              },
              {
                "Idiom": "Rack one's brain",
                "Define": "(Also rack one's brains) To think very hard about something. ",
                "Ex": "I've racked my brain all day long, but I still can't remember where I put the keys. "
              },
              {
                "Idiom": "Raise eyebrows",
                "Define": "To cause surprise or mild disapproval. ",
                "Ex": "The way the children behaved raised a few eyebrows the hosts."
              },
              {
                "Idiom": "Save face",
                "Define": "To take an action or make a gesture intended to preserve one's reputation or honor. ",
                "Ex": "They tried to win their last match in the championship just to save face. "
              },
              {
                "Idiom": "Send shivers down one's spine",
                "Define": "To terrify; to make someone feel extremely nervous. ",
                "Ex": "Hearing that her rapers escaped prison sent shivers down her spine. "
              },
              {
                "Idiom": "Send shivers down someone's spine",
                "Define": "To terrify; to make someone feel extremely nervous. ",
                "Ex": "Hearing that the killer escaped prison sent shivers down my spine. "
              },
              {
                "Idiom": "Shake a leg",
                "Define": "Used to tell someone to rouse themselves from sleep and get out of bed.",
                "Ex": "Shake a leg or we'll miss the party! "
              },
              {
                "Idiom": "Shoulder to cry on",
                "Define": "Said about a person someone to whom you can tell your problems to and then ask for sympathy, emotional support and advice.",
                "Ex": "Lacy needs a shoulder to cry on. Her father died yesterday. "
              },
              {
                "Idiom": "Skin and bones",
                "Define": "To be underweight and look bad, to be extremely thin. ",
                "Ex": "Because of her illness she was nothing but skin and bones. "
              },
              {
                "Idiom": "Skin someone alive",
                "Define": "To punish someone severely. ",
                "Ex": "My parents will skin me alive if they see my grades."
              },
              {
                "Idiom": "Stiff upper lip",
                "Define": "One who has a stiff upper lip displays fortitude in the face of adversity, or exercises self-restraint in the expression of emotion. ",
                "Ex": "He always has a stiff upper lip. He never complains. "
              },
              {
                "Idiom": "Take the bit between one's teeth",
                "Define": "To take charge. ",
                "Ex": "The company needed a new manager for the project. So he took the bit between his teeth. "
              },
              {
                "Idiom": "Tear your hair out",
                "Define": "Said when you are feeling a lot of anxiety over a problem. ",
                "Ex": "He's been tearing his hair out over his deteriorated relationship with his wife. "
              },
              {
                "Idiom": "To cost an arm and a leg",
                "Define": "(Also cost a bomb, the earth, a packet, a small fortune) Extremely expensive. ",
                "Ex": "I'd love to buy a Rolls-Royce, but it costs an arm and a leg. "
              },
              {
                "Idiom": "To wash one's hands of",
                "Define": "To absolve oneself of responsibility or future blame for. ",
                "Ex": "I wash my hands of this whole affair. "
              },
              {
                "Idiom": "Under one's nose",
                "Define": "1. Directly in front of one; clearly visible. 2. Obvious or apparent. 3. In someone's presence.",
                "Ex": "1. I searched for my glasses for twenty minutes, and finally found them right under my nose. 2. If we had paid more attention, we would have found that the answer was under our noses the whole time. 3. They were stealing his money just under his nose.  "
              },
              {
                "Idiom": "Under one’s thumb",
                "Define": "Completely controlled by someone; at someone’s command. ",
                "Ex": "She has her husband under her thumb. He would do anything for her. "
              },
              {
                "Idiom": "Wag one's chin",
                "Define": "To talk. ",
                "Ex": "Stop wagging your chin and do something."
              },
              {
                "Idiom": "Wait on someone hand and foot",
                "Define": "To serve someone well, satisfying all personal needs. ",
                "Ex": "She can't take care of herself. She always needs someone to wait on her hand and foot."
              },
              {
                "Idiom": "Watch one's mouth",
                "Define": "To be careful about what one says, especially with regard to disrespectful or profane language. ",
                "Ex": "Watch your mouth when you speak to him. He's the boss. "
              },
              {
                "Idiom": "Yoke around someone's neck",
                "Define": "A burden. ",
                "Ex": "Try to stop the fighting of two people was a yoke around his neck, they both hurt him badly, for coming in between. "
              }
            ],
            "Relationship": [
              {
                "Idiom": "Affinity for",
                "Define": "Said about you have attraction, preference or sympathy for something or someone. ",
                "Ex": "He has an affinty for classical music."
              },
              {
                "Idiom": "Bad blood",
                "Define": "Unpleasant feeling between different people.",
                "Ex": "There is bad blood between Nancy and Leila. They are rarely in good terms with each other. "
              },
              {
                "Idiom": "Be an item",
                "Define": "Said about a couple when they are having a romantic relationship.",
                "Ex": "I heard that Leila and Joe are an item."
              },
              {
                "Idiom": "Blood is thicker than water",
                "Define": "Family relations are more important than all other relationships. ",
                "Ex": "Even if Nancy and her brother often argue, they always forgive each other. Blood is thicker than water. "
              },
              {
                "Idiom": "Build bridges",
                "Define": "To improve relationships between people. ",
                "Ex": "They wanted to build bridges between Nancy and Alan to settle the conflict once for all. "
              },
              {
                "Idiom": "Don't get me wrong",
                "Define": "An expression said when you fear someone does not understand what you say.",
                "Ex": "Don't get me wrong but I think your plan may not work."
              },
              {
                "Idiom": "Everybody and his cousin",
                "Define": "Everybody; a huge crowd; too many people",
                "Ex": "Everybody and his cousin will be in line for opening night with free popcorn!"
              },
              {
                "Idiom": "Expectant mother",
                "Define": "A pregnant woman. ",
                "Ex": "There are many good tips for expectant mothers in this little book. "
              },
              {
                "Idiom": "Experience is the mother of wisdom",
                "Define": "This idiom is used to mean that people learn from what happens to them. ",
                "Ex": "You will never understand the love parents have for their children until you get your own children. Experience is really the mother of wisdom. "
              },
              {
                "Idiom": "Face (that) only a mother could love",
                "Define": "A very ugly face. ",
                "Ex": "Look at that poor girl. That's a face that only a mother could love."
              },
              {
                "Idiom": "Fair-weather friend",
                "Define": "Someone who is your friend only when the times are good. ",
                "Ex": "Don't rely on him. He's a fair-weather friend. "
              },
              {
                "Idiom": "Fall in love",
                "Define": "To begin feeling attracted to someone and love him or her.",
                "Ex": "When Jane and Math met for the first time, they fell madly in love. "
              },
              {
                "Idiom": "Father figure",
                "Define": "The phrase father figure usually refers to an older man who is respected and who is characterized by power, authority, or strength.",
                "Ex": "The kids respected him as a father figure."
              },
              {
                "Idiom": "Get on like a house on fire",
                "Define": "Said about two people like each other and become very close friends in a very short time. ",
                "Ex": "The two ladies are getting on like a house on fire. "
              },
              {
                "Idiom": "Go against the flow",
                "Define": "To do the opposite of people do and not accept things as they are.",
                "Ex": "In his last speech, the leader of the opposition went against the flow and declared that reducing taxes will harm the economy."
              },
              {
                "Idiom": "Go with the flow",
                "Define": "To do what people do and accept things as they are.",
                "Ex": "Don't worry too much! Take it easy and go with the flow! "
              },
              {
                "Idiom": "Good fences make good neighbors",
                "Define": "(Also good walls make good neighbors) This means that people should respect other people's property and privacy and mind their own business. ",
                "Ex": "Our neighbor should prevent his children from messing up our lawn. Good fences make good neighbors. "
              },
              {
                "Idiom": "Have friends in high places",
                "Define": "To know powerful people. ",
                "Ex": "Don't worry about the problem. I have friends in high places. "
              },
              {
                "Idiom": "He that would the daughter win, must with the mother first begin",
                "Define": "This is a proverb which means that if you intend to marry a woman, first try to win her mother on your side. ",
                "Ex": "Listen Joe, if you want to marry Nancy, try to impress her mother first and be sure that she is on your side. He that would the daughter win, must with the mother first begin. "
              },
              {
                "Idiom": "Join the club",
                "Define": "Said as a reply to someone to mean that they are in the same situation. ",
                "Ex": "Nancy: I've got problems with my husband these days. Alice: Join the club!"
              },
              {
                "Idiom": "Kissing cousin",
                "Define": "A distant relative. ",
                "Ex": "I and Daniel are kissing cousins. "
              },
              {
                "Idiom": "Like mother, like daughter",
                "Define": "This is a proverb which means that daughters resemble their mothers. ",
                "Ex": "Like mother, like daughter. Liza's mother is mad about chocolate, too. "
              },
              {
                "Idiom": "Love-hate relationship",
                "Define": "An interpersonal relationship involving simultaneous or alternating emotions of love and hate. ",
                "Ex": "He has a love-hate relationship with his mother."
              },
              {
                "Idiom": "Necessity is the mother of invention",
                "Define": "This proverb means that when people really need to do something, they will find a way to do it. ",
                "Ex": "When her pen had run out of ink, she used her lipstick to write a short note to her husband who was at work."
              },
              {
                "Idiom": "Play a joke",
                "Define": "(Also play trick) To deceive someone for fun.",
                "Ex": "On April fool's day some people play practical jokes on their friends. "
              },
              {
                "Idiom": "Play second fiddle",
                "Define": "To take a subordinate or weaker position than someone else.",
                "Ex": "Bill doesn't want to play second fiddle to his colleague any more. He feels he is more trained and more experienced."
              },
              {
                "Idiom": "Raw deal",
                "Define": "Said when someones is ill treated. ",
                "Ex": "Mary got a raw deal. She was innocent, but she had to pay a big fine."
              },
              {
                "Idiom": "Say uncle",
                "Define": "To admit failure. ",
                "Ex": "I'll show them how I can be a superstar. I'm not going to say uncle. "
              },
              {
                "Idiom": "The mother of all",
                "Define": "An extreme example which is the biggest, most impressive, or most important of its kind.",
                "Ex": "Failure is the mother of all success. "
              },
              {
                "Idiom": "Them and us",
                "Define": "Used when describing disagreements or differences especially between different social groups",
                "Ex": "There is a them and us situation in the company after the disagreement between the boss and his workers about the working conditions."
              },
              {
                "Idiom": "You can't please everyone",
                "Define": "Making everyone happy is impossible no matter what you do. ",
                "Ex": "When the boss chose Mary as the chief executive of the project, everybody complained. But you can't please everyone! "
              }
            ],
            "Religion": [
              {
                "Idiom": "Act of God",
                "Define": "Something that ooccured, such as an accident, for which no human is responsible. A natural disaster such as a storm, earthquake...",
                "Ex": "The Haiti earthquake was really an act of God."
              },
              {
                "Idiom": "Adam's ale",
                "Define": "(Old-fashioned) water. ",
                "Ex": "Take a glass of adam's ale if you are thirsty. "
              },
              {
                "Idiom": "As patient as Job",
                "Define": "If someone is as patient as Job, they are very patient. The person who shows great endurance through all sorts of trials is said to have the patience of Job. This idiom is a simile related to the religious figure Job mentioned as a prophet in all Abrahamic religions: Judaism, Christianity, and Islam. Job is presented as a good and prosperous family man who is be set with hideous and dreadful events that bereft him of his loved ones, his health and all his property. His struggle and his patience to understand his situation leads him to get a reward from God by restoring his health, doubling his original wealth and giving him a lot of children and grandchildren.   ",
                "Ex": "If you want to work with that temperamental woman you must be as patient as Job."
              },
              {
                "Idiom": "As poor as a church mouse",
                "Define": "If someone is as poor as a church mouse they are extremely poor. An other similar phrase is hungry as a church mouse. The phrase is derived from the fact that church buildings don't store or provide food and therefore mice in such buildings were utterly destitute.",
                "Ex": "He is as poor as a church mouse; don't ask him to donate anything. "
              },
              {
                "Idiom": "As ugly as sin",
                "Define": "If something is as ugly as sin, it is very ugly. The term sin has a religious connotation. It refers to a violation of God's will, a misdeed.  ",
                "Ex": "Jesus Christ! The dress she is wearing is as ugly as sin."
              },
              {
                "Idiom": "Baptism of fire",
                "Define": "The idiom baptism of fire refers to a very difficult first experience someone undergoes. The term baptism is a religious ceremony in which one is initiated, purified, or given a name. ",
                "Ex": "My first day as the manager of the restaurant was a real baptism of fire. "
              },
              {
                "Idiom": "Bear one's cross",
                "Define": "If someone bear one's cross they endure burden or difficulties. A cross is a Christian religious symbol. It is viewed as a symbol of Jesus's crucifixion.  ",
                "Ex": "I know it is a difficult situation you are experiencing, but you have to bear your own cross. I can't help you. I am sorry."
              },
              {
                "Idiom": "Better the devil you know",
                "Define": "(Also better the devil you know than the devil you don't) It is sometimes better to deal with someone or thing you know than to deal with a new person or thing who could be even worse. ",
                "Ex": "Nancy is such a difficult girl to work with, but better the devil you know. "
              },
              {
                "Idiom": "Between the devil and the deep blue sea",
                "Define": "The phrase between the devil and the deep blue sea is an idiom referring to a dilemma, a choice between two undesirable situations. The phrase was first used by Robert Monro in his expedition with the worthy Scots regiment called Mac-keyes, 1637: I, with my partie, did lie on our poste, as betwixt the devill and the deep sea. A variation of this idiom is: Between a rock and a hard place.",
                "Ex": "She is caught between the devil and the deep blue sea. She has to choose between staying with her nasty husband and leaving him, taking care of her children all alone. "
              },
              {
                "Idiom": "By the grace of God",
                "Define": "Through the kindness and help of God. ",
                "Ex": "By the grace of God, his son managed to pass the exam. "
              },
              {
                "Idiom": "Cold day in hell",
                "Define": "This idiom is used to mean that something will never happen.",
                "Ex": "It'll be a cold day in hell before I accept his apologies. "
              },
              {
                "Idiom": "Come hell or high water",
                "Define": "Said when you are determined to do something despite difficulties.",
                "Ex": "I'll finish my report by midnight, come hell or high water! "
              },
              {
                "Idiom": "Crux of the matter",
                "Define": "The phrase crux of the matter refers to the most important point of an issue. Another related idiom is heart of the matterCrux has a Latin origin referring to a real cross and its association with torment. In English, the term means difficulty.   ",
                "Ex": "I think his new revelations about the company is the crux of the matter."
              },
              {
                "Idiom": "Devil-may-care",
                "Define": "Careless, reckless or defiant. ",
                "Ex": "He has a rather devil-may-care attitude to his wife's illness."
              },
              {
                "Idiom": "Eye for an eye, a tooth for a tooth",
                "Define": "The phrase eye for an eye, a tooth for a tooth refers to a principle found in Babylonian Law, in the Code of Hammurabi, as well as in monotheist religions - Judaism, Christianity and Islam. According to this principle, a person who has injured another person is penalized to a similar degree. ",
                "Ex": "If he killed the poor woman, he deserves to die. It's as simple as that - an eye for an eye, tooth for a tooth. "
              },
              {
                "Idiom": "Fall from grace",
                "Define": "The idiom fall from grace refers to a loss of status, respect, or prestige.The idiom comes from a Christian reference to the transition of the first man and woman from a state of innocent obedience to God to a state of guilty disobedience.    ",
                "Ex": "The politician has fallen from grace and has become very unpopular. "
              },
              {
                "Idiom": "Get religion",
                "Define": "If you get religion, you decide to become religious or to behave in an ethical way and end one's immoral behavior. ",
                "Ex": "After the terrible accident he had had, Allan got religion and joined the church."
              },
              {
                "Idiom": "Go to hell in a handbasket",
                "Define": "Go to a bad state of affairs quickly. ",
                "Ex": "He said that all their plans for the project went to hell in a handbasket because of the recession. "
              },
              {
                "Idiom": "God forbid",
                "Define": "(Also Heaven forbid) Said when you hope that something does not happen. ",
                "Ex": "God forbid that they encounter a problem on their way home. "
              },
              {
                "Idiom": "God knows",
                "Define": "This idiom means only God knows said when you have absolutely no knowledge of something.",
                "Ex": "God knows what he's going to do after his divorce. "
              },
              {
                "Idiom": "High man on the totem pole",
                "Define": "The most important person in a hierarchy or organization. (Opposite: low man on the totem pole) ",
                "Ex": "I want to meet the hign man on the totem pole. "
              },
              {
                "Idiom": "In God's name",
                "Define": "(Also in the name of God, in the name of heaven, in God's name, in heaven's name) Used to add emphasis.",
                "Ex": "What in God's name did you do to that poor girl?  "
              },
              {
                "Idiom": "Low man on the totem pole",
                "Define": "The least important man in a hierarchy or organization. (Opposite: high man on the totem pole) ",
                "Ex": "He's not the man to talk to. He's just the low man on the totem pole. "
              },
              {
                "Idiom": "Mecca for someone or something",
                "Define": "If a place is a Mecca for someone or something, it is a place that a lot of people visit because it is known for something that they want to see or do. Mecca is the religious city of Islam. It is a city where Muslims go for pilgrimage. ",
                "Ex": "Milan is a Mecca for fashion. "
              },
              {
                "Idiom": "Not have a snowball's chance in hell",
                "Define": "(Also not have a cat in hell's chance) Not to be able to achieve something. ",
                "Ex": "He hasn't a snowball's chance of getting the money he needs for the project."
              },
              {
                "Idiom": "Poverty is no sin",
                "Define": "The phrase poverty is no sin means that we shouldn't condemn people for their poverty. ",
                "Ex": "It is a pity that the police are chasing those beggars. Poverty is no sin. "
              },
              {
                "Idiom": "Preach to the choir",
                "Define": "The phrase preach to the choir or preach to the converted means to ​try to convince people of something that they already ​believe. ",
                "Ex": "You are just preaching to the choir. It is pointless to convince us of the value of exercising. We all agree that exercising is good for our health."
              },
              {
                "Idiom": "Religious about doing something",
                "Define": "If someone is religious about doing something, they are strict and conscientious about it.  ",
                "Ex": "He is religious about respecting the law."
              },
              {
                "Idiom": "Speak of the devil",
                "Define": "The phrase speak of the devil is the short form of the idiom speak of the devil and he shall appear. It is used about someone who appears unexpectedly while being talked about.  ",
                "Ex": "Speak of the Devil! look who's coming. "
              },
              {
                "Idiom": "Until hell freezes over",
                "Define": "If someone say that someone can do something until hell freezes over, they mean that one will never in their life get the results that they want.",
                "Ex": "He can wait for my approval until hell freezes over - He won't marry my daughter."
              }
            ],
            "Sexuality": [
              {
                "Idiom": "A bit of fluff",
                "Define": "(Also a bit of skirt.) A sexually attractive woman. ",
                "Ex": "I saw him yesterday with a bit of fluff. "
              },
              {
                "Idiom": "Be better than sex",
                "Define": "Said about something which is very enjoyable or exciting.",
                "Ex": "Riding a horse is a real fun. It's better than sex. "
              },
              {
                "Idiom": "Come out of the closet",
                "Define": "The phrase come out of the closet means to admit publicly one's homosexuality.",
                "Ex": "He came out of the closet when he went to university. "
              },
              {
                "Idiom": "Facts of life",
                "Define": "The details about sex and reproduction. ",
                "Ex": "His parents told him the facts of life when he was ten years old."
              },
              {
                "Idiom": "Have the hots for someone",
                "Define": "To be strongly sexually attracted to someone. ",
                "Ex": "He has the hots for her but he can't tell her. He's so shy. "
              },
              {
                "Idiom": "In the family way",
                "Define": "(Also in a family way.) Pregnant. ",
                "Ex": "I've heard that Leila is in the family way. Is that true? "
              },
              {
                "Idiom": "Meat and two veg",
                "Define": "The male genitals. ",
                "Ex": "Everybody could see his meat and two veg because his trousers were so tight. "
              },
              {
                "Idiom": "Play away from home",
                "Define": "To be unfaithful; to have sex with someone who is not your usual partner. ",
                "Ex": "She stuck on her decision to divorce because she discoverd her husband playing away from home. "
              },
              {
                "Idiom": "Play the field",
                "Define": "To have many sexual relationships. ",
                "Ex": "He's not the kind of person to think of getting married. He's quite happy to play the field. "
              },
              {
                "Idiom": "Shotgun marriage",
                "Define": "(Also shotgun wedding) When the bridegroom is forced to marry the bride he made her pregnant. ",
                "Ex": "It was a shotgun marriage. Nancy was three months pregnant when she married John. "
              },
              {
                "Idiom": "The battle of the sexes",
                "Define": "The phrase the battle of the sexes refers to the conflicts and disagreements between men and women.",
                "Ex": "Gender equality is meant to end the battle of the sexes. "
              },
              {
                "Idiom": "Virgin territory",
                "Define": "A territory that hasn't been touched or explored. ",
                "Ex": "There is no sign of humans in that island. It's a virgin territory. "
              },
              {
                "Idiom": "X-rated",
                "Define": "Not suitable for children. ",
                "Ex": "Children ae not allowed to watch this film. It's X-rated."
              }
            ],
            "Sport": [
              {
                "Idiom": "Beat someone at their own game",
                "Define": "The phrase beat someone at his or her own game means to outdo someone using their own methods, tactics or specialty.",
                "Ex": "I think we are able to beat our competitors at their own game. "
              },
              {
                "Idiom": "A lost ball in the weeds",
                "Define": "The phrase a lost ball in the weeds refers to a person who is completely lost or confused and does not know what they are doing, how to do it or possibly even where they are.",
                "Ex": "I got confused as to what I should do. I was a lost ball in the weeds. "
              },
              {
                "Idiom": "A whole new ball game",
                "Define": "A completely different situation.",
                "Ex": "He has written so many short stories but writing a novel is a whole new ball game."
              },
              {
                "Idiom": "As bald as a cue ball",
                "Define": "(Also as bald as a coot) Completely bald.",
                "Ex": "His father was as bald as a cue ball! "
              },
              {
                "Idiom": "Ball of fire",
                "Define": "A person who is especially hard-working, high-achieving, ambitious, or active. ",
                "Ex": "They say he is a real ball of fire. He has already demonstrated his wish to climb higher. "
              },
              {
                "Idiom": "Ball someone or something up",
                "Define": "1. To make a mess of, destroy or ruin; to interfere with someone or something. 2. To roll something up into a ball.  ",
                "Ex": "1. Someone has balled my car up. 2. She balled the paper up in anger.        "
              },
              {
                "Idiom": "Ball-breaker",
                "Define": "(Also a ball-buster.) This refers either to a job or situation that is demanding and arduous and punishing or to a demanding woman who destroys men's confidence.",
                "Ex": "My job is such a ball-breaker! My boss expects me to work over the weekend again."
              },
              {
                "Idiom": "Be on the ball",
                "Define": "To be well-informed and respond promptly. ",
                "Ex": "We need someone who's on the ball to help us implement our plan."
              },
              {
                "Idiom": "Beat a retreat",
                "Define": "To leave hastily in the face of opposition. ",
                "Ex": "When they saw the police coming, they beat a retreat. "
              },
              {
                "Idiom": "Beats me",
                "Define": "(Also it beats me) I don't know; I have no idea. ",
                "Ex": "Mickeal: What's the longest river in the world. Alan: Beats me!        "
              },
              {
                "Idiom": "Carry the ball",
                "Define": "To take charge and control of an activity and be considered reliable enough to do a job. ",
                "Ex": "He can't carry the ball. He isn't reliable. "
              },
              {
                "Idiom": "Come down on somebody like a ton of bricks",
                "Define": "To hit or punish somebody.",
                "Ex": "I'll come down on you like a ton of bricks if you do that once again! "
              },
              {
                "Idiom": "Come from behind",
                "Define": "To win after being in a losing position in a game.",
                "Ex": "The young boxer came from behind to beat the world champion."
              },
              {
                "Idiom": "Come to blows",
                "Define": "To have fight or an argument with someone.",
                "Ex": "Negotiators are trying not to come into blows over their territorial dispute."
              },
              {
                "Idiom": "In a dead heat",
                "Define": "Said when two or more competitors finish a race or a competition at exactly the same time or with exactly the same result.",
                "Ex": "The two horses finished the race in a dead heat."
              },
              {
                "Idiom": "It beats me",
                "Define": "Used to suggest that you don't understand something. ",
                "Ex": "It beats me how she passed the exam. "
              },
              {
                "Idiom": "Keep one's eye on the ball ",
                "Define": "To remain alert to the events occurring around oneself. ",
                "Ex": "To be successful in this business, you'll have to keep your eye on the ball. "
              },
              {
                "Idiom": "Play ball",
                "Define": "To cooperate and agree to work with others.",
                "Ex": "The manager asked him to play ball if he wants things to go well."
              },
              {
                "Idiom": "Set the ball rolling",
                "Define": "(Also start or get the ball rolling) Start something, especially a conversation or a social event.",
                "Ex": "There was a quiet atmosphere in the party so I decided to set the ball rolling and got up to dance. "
              },
              {
                "Idiom": "That beats everything",
                "Define": "(Also that beats all) Expressions of surprise. ",
                "Ex": "You mean he came very late again last night  Well, that beats everything! "
              },
              {
                "Idiom": "The ball is in someone's court",
                "Define": "When the ball is in someone's court they have to take action.",
                "Ex": "The ball is in your court now. You should decide what you want to do. "
              },
              {
                "Idiom": "Throw in the towel",
                "Define": "(Also throw in the sponge) To admit defeat.",
                "Ex": "After a long fight against his enemies, he finally threw the towel."
              },
              {
                "Idiom": "Wait for the ball to drop",
                "Define": "To wait in expectation of an occurrence. ",
                "Ex": "When the scandal was publicly revealed, he waited for the ball to drop as he was involved. "
              },
              {
                "Idiom": "Walk the talk",
                "Define": "To do what one said one could do, or would do, not just making empty promises. ",
                "Ex": "If we advise people to take care of the environment, we have to walk the talk. "
              },
              {
                "Idiom": "Weekend warrior",
                "Define": "A person who indulges in a sport or past-time on an infrequent basis, usually on weekends when work commitments are not present. ",
                "Ex": "The most common foot related injury I see for the weekend warrior is heel pain. "
              },
              {
                "Idiom": "What beats me",
                "Define": "Said when you do not understand a situation or someone's behaviour.",
                "Ex": "What beats me is how he passed the exam."
              }
            ],
            "Science": [
              {
                "Idiom": "Acid test",
                "Define": "The idiom acid test refers to a decisive test whose findings show the worth or quality of something.",
                "Ex": "Our team's next match will be the first real acid test in this competition. "
              },
              {
                "Idiom": "Bells and whistles",
                "Define": "The phrase bells and whistles refers to extra, fancy add-ons or gadgets on something like a phone, a car or a computer.    ",
                "Ex": "This cars is loaded with all the bells and whistles, but it's too expensive."
              },
              {
                "Idiom": "Blow a fuse",
                "Define": "Become uncontrollably angry; lose your temper. ",
                "Ex": "Hey, don't blow a fuse. "
              },
              {
                "Idiom": "Bright as a button",
                "Define": "Intelligent. ",
                "Ex": "He has a daughter who is as bight as a button. "
              },
              {
                "Idiom": "Button (up) one's lip",
                "Define": "To stop talking. ",
                "Ex": "Please, button up your lip and keep the news secret till tomorrow. "
              },
              {
                "Idiom": "Cog in the machine",
                "Define": "(Also a cog in the wheel) said about one part of a large system or organization. ",
                "Ex": "He was just an important cog in the machine of organized crime. "
              },
              {
                "Idiom": "Dad fetch my buttons",
                "Define": "Said to express a surprise. ",
                "Ex": "Dad fetch my buttons! He won a lot of money in the lottery."
              },
              {
                "Idiom": "Have something down to a science",
                "Define": "Said when you are able to manage doing something very well. ",
                "Ex": "They have the management of the concert down to a science. "
              },
              {
                "Idiom": "Hit the panic button",
                "Define": "(Also press or push the button) To panic suddenly. ",
                "Ex": "Relax! Don't hit the button it's just the wind. "
              },
              {
                "Idiom": "In tune (with somebody or something)",
                "Define": "Said when you have a good understanding of someone or something. ",
                "Ex": "He was in tune with new technologies. "
              },
              {
                "Idiom": "It's not rocket science",
                "Define": "If something is not rocket science, it is not difficult to understand.       ",
                "Ex": "1. It's just an easy math problem. It isn’t rocket science. 2. You don't have to be a rocket scientist to figure out the solution.    "
              },
              {
                "Idiom": "On the same wavelength",
                "Define": "Thinking in the same pattern or in agreement. ",
                "Ex": "They've done a good job because they were on the same wavelength. "
              },
              {
                "Idiom": "Pull the plug",
                "Define": "The phrase pull the plug means to put an end to an activity, preventing it from continuing.",
                "Ex": "They are going to pull the plug on the new TV show because it didn't get any sponsors."
              },
              {
                "Idiom": "Push someone's buttons",
                "Define": "(Also press someone's buttons) draw a strong emotional reaction from someone, especially anger or sexual arousal. ",
                "Ex": "Don't push my buttons with your silly comments. "
              },
              {
                "Idiom": "Reinvent the wheel",
                "Define": "To waste one's time doing something that has already been done satisfactorily. ",
                "Ex": "Just use our guide book to and don't try to reinvent the wheel. "
              },
              {
                "Idiom": "Run out of steam",
                "Define": "If you run out of steam, you lose the energy, enthusiasm or interest to continue doing something. ",
                "Ex": "After having worked for twenty years as a the manager of the company, he seems to run out of steam."
              },
              {
                "Idiom": "Spuntnick moment",
                "Define": "The phrase Sputnik moment refers to a moment of challenge when a society or person realizes they must work harder to surpass their competitors. The phrase was popularized by Barack Obama in his State of the Union address in 2011. The origin of the idiom comes from the Soviet Union's 1957 launch of the first Earth-orbiting satellite Sputnik 1, which was a great achievement at that moment, while the US were lagging behind in space technology. This caused the space race to start between the two countries. The US ultimately won the race in 1969 with the first human landing on the Moon.",
                "Ex": "This generation's Sputnik moment has arrived, President Barack Obama declared in his State of the Union address, referring to the United States' need to invest in research and development to revive the economy and ensure future stability. "
              },
              {
                "Idiom": "The dismal science",
                "Define": "The phrase the dismal science refers to the discipline of economics. The term drew a contrast with the phrase gay science which refers to song and verse writing. The phrase the dismal science first occurs in Thomas Carlyle's 1849 tract called Occasional Discourse on the Negro Question, in which he argued in favor of re-introducing slavery in order to regulate the labor market in the West Indies: Not a gay science, I should say, like some we have heard of; no, a dreary, desolate and, indeed, quite abject and distressing one; what we might call, by way of eminence, the dismal science.Carlyle, Thomas (1849). Occasional Discourse on the Negro Question, Fraser's Magazine for Town and Country, Vol. XL., p. 672.",
                "Ex": "He is interested in history and the dismal science. "
              },
              {
                "Idiom": "Well-oiled machine",
                "Define": "The phrase well-oiled machine refers to something that operates well. ",
                "Ex": "Their office ran like a well-oiled machine."
              }
            ],
            "Time": [
              {
                "Idiom": "Against the clock",
                "Define": "To work or race against the clock means to do something as fast as possible and try to finish it before a deadline. ",
                "Ex": "The students were racing against the clock to finish the paper before the deadline. "
              },
              {
                "Idiom": "Against time",
                "Define": "(Also against the clock) An attempt to finish something quickly within a time limit. ",
                "Ex": "It's going to be a race against the time to finish the project before the deadline. "
              },
              {
                "Idiom": "Ahead of one's time",
                "Define": "In advance of concurrent commonly accepted ideas; showing characteristics of changes yet to be; present in one's work before later advances in the field. ",
                "Ex": "With his new scientific discoveries, he was ahead of his time. "
              },
              {
                "Idiom": "Any minute soon now",
                "Define": "(Also any moment or second or time now) very soon",
                "Ex": "The news about the president's resignation will be broadcasted on TV any moment now."
              },
              {
                "Idiom": "Beat the clock",
                "Define": "To do something before a deadline. ",
                "Ex": "They managed to beat the clock and arrive a few minutes before the conference started."
              },
              {
                "Idiom": "Call it a day",
                "Define": "To stop working for the rest of the day.",
                "Ex": "Why don't we call it a day? I'm really tired. "
              },
              {
                "Idiom": "Clock on",
                "Define": "To register one's arrival at work. ",
                "Ex": "They clocked on as soon as they arrived at work. "
              },
              {
                "Idiom": "Clock out",
                "Define": "(Also clock off) To register one's departure from work. ",
                "Ex": "They clocked out early in order to be on time for the concert. "
              },
              {
                "Idiom": "Devil of a time",
                "Define": "If you have a devil of a time, you have a very difficult time.",
                "Ex": "Before she divorced, Ann had had a devil of a time with my her husband."
              },
              {
                "Idiom": "Drastic times call for drastic measures",
                "Define": "The idiom drastic times call for drastic measures means that when you face extreme and undesirable situations, it is sometimes necessary to take extreme actions. Desperate times call for desperate measures is another variation of the idiom.   ",
                "Ex": "We had to let go five of our workers because the company had problems selling the new product. Drastic times call for drastic measures!"
              },
              {
                "Idiom": "Every minute",
                "Define": "Describing the whole period that something lasted.",
                "Ex": "I enjoyed every minute of the match. It was just fantastic. "
              },
              {
                "Idiom": "High time",
                "Define": "If it's high time you did something, it is the appropriate time for it.   ",
                "Ex": "It's high time you began learning how to drive. "
              },
              {
                "Idiom": "In a split second",
                "Define": "In just very short time. ",
                "Ex": "Every thing was calm. But just in a split second a storm hit the whole region causing a lot of victims. "
              },
              {
                "Idiom": "In the nick of time",
                "Define": "The phrase in the nick of time means at the last possible moment, just before it's too late. The word nick refers to a notch, cut, or indentation on an edge or a surface.   ",
                "Ex": "I arrived at the train station in the nick of time and took the last train to the capital city."
              },
              {
                "Idiom": "It's about time",
                "Define": "Used to express impatience at the eventual occurrence of something that should have occurred a long time ago. ",
                "Ex": "It's about time that women should be considered equal to men in this country. "
              },
              {
                "Idiom": "Kill time",
                "Define": "To kill time means to spend time doing nothing in particular.",
                "Ex": "He had nothing in particular to do, so he went for a walk downtown to kill time. "
              },
              {
                "Idiom": "Matter of time",
                "Define": "The phrase it is only a matter if time is used to say that something will certainly happen.   ",
                "Ex": "It is only a matter of time before he resigns."
              },
              {
                "Idiom": "Moment in the sun",
                "Define": "A brief instance in which an otherwise obscure, unremarkable, or humble person draws attention. ",
                "Ex": "That band got their moment in the sun during the 70s. "
              },
              {
                "Idiom": "Moment of truth",
                "Define": "A deciding instant; the time when a test determines or makes it apparent whether something will succeed. ",
                "Ex": "This is the moment of truth, answer the questions of the test. "
              },
              {
                "Idiom": "Name the day",
                "Define": "Fix the date of an important event, especially marriage.",
                "Ex": "Sarah and John are going to name the day soon."
              },
              {
                "Idiom": "Not for a minute",
                "Define": "Not at all.",
                "Ex": "I don't want you to fail in your project. Not for a minute. "
              },
              {
                "Idiom": "Now or never",
                "Define": "Said when you have to do something right now because you may not get another chance to do it later. ",
                "Ex": "This is your chance. It's now or never!"
              },
              {
                "Idiom": "Pressed for time",
                "Define": "If you are pressed for time, it means that you are in a hurry.",
                "Ex": "I am sorry, I can't talk to you right now; I'm pressed for time. "
              },
              {
                "Idiom": "Question of time",
                "Define": "(Also a matter of time) Said about something that will surely happen. ",
                "Ex": "The criminal will be arrested. It's just a question of time."
              },
              {
                "Idiom": "Race against time",
                "Define": "To race against time means to hurry to do something before a deadline.   ",
                "Ex": "They had only two days to finish the job, so they had to race against time."
              },
              {
                "Idiom": "Rome wasn't built in a day",
                "Define": "Said to emphasize that great work takes time to do. Nothing of importance can be done in a short period of time.",
                "Ex": "Don't expect immediate outstanding earnings fom your new bussiness. Rome wasn't built in a day. "
              },
              {
                "Idiom": "Rough time",
                "Define": "The idiom rough time means a hard or bad time.      ",
                "Ex": "It was such a rough time. "
              },
              {
                "Idiom": "Stand the test of time",
                "Define": "If something stands the test of time, it lasts for a long time. ",
                "Ex": "Their marriage has stood the test of time."
              },
              {
                "Idiom": "The minute (that)",
                "Define": "At the moment when",
                "Ex": "The minute he saw her, he fell in love."
              },
              {
                "Idiom": "Time flies",
                "Define": "The phrase time flies means that time passes very quickly especially when you're having fun. Its Latin origin is tempus fugit",
                "Ex": "Time flew while they were talking about the old beautiful days. "
              },
              {
                "Idiom": "To the last",
                "Define": "Until the completion of something or until death.",
                "Ex": "1. Don't worry I'll support to the last. 2. She was a great lady to the last.        "
              },
              {
                "Idiom": "To this day",
                "Define": "Until now.",
                "Ex": "He disappeared and to this day nobody knows what happened to him. "
              },
              {
                "Idiom": "Turn back the clock",
                "Define": "(Also wind back the clock or roll back the clock) figuratively to return in time to an earlier period of history.",
                "Ex": "When their relationship had started deteriorating, he told her that they should turn back the clock and just go back to when things were simpler. "
              },
              {
                "Idiom": "Under the wire",
                "Define": "At the last minute; before the deadline; barely on time; nearly late. ",
                "Ex": "He turned his report just under the wire. "
              },
              {
                "Idiom": "Up to the minute",
                "Define": "The most modern",
                "Ex": "The internet is an excellent source of up to the minute news. "
              },
              {
                "Idiom": "Up-to-date",
                "Define": "Current; recent; the latest ",
                "Ex": "He uses an up-to-date theory to explain his views. "
              },
              {
                "Idiom": "Watch the clock",
                "Define": "To keep noticing the clock because you are eager to stop what you are doing. ",
                "Ex": "If you are someone who watches the clock, then this job is not for you."
              },
              {
                "Idiom": "Zero hour",
                "Define": "The time when something is planned to begin (military) ",
                "Ex": "This is the zero hour for the attack, said the sergeant. "
              }
            ],
            "Travel": [
              {
                "Idiom": "Any port in a storm",
                "Define": "An unfavorable option which might well be avoided in good times but which nevertheless looks better than the alternatives at the current time. ",
                "Ex": "That horrible hotel was a case of any port in a storm as we couldn't find any place to spend the night. "
              },
              {
                "Idiom": "At the wheel",
                "Define": "Driving; in control of a vehicle. ",
                "Ex": "You know he fell asleep at the wheel. They were so lucky they didn't have an accident."
              },
              {
                "Idiom": "Backseat driver",
                "Define": "1. A passenger in a car who insists on giving the driver directions. 2. Anybody offering unsolicited or unwelcome advice.",
                "Ex": "My brother is such a backseat driver. I hate traveling with him. "
              },
              {
                "Idiom": "Drive a hard bargain",
                "Define": "Negotiate forcefully. ",
                "Ex": "It's gonna be a tough negotiations with them. They drive a hard bargain. "
              },
              {
                "Idiom": "Drive someone up the wall",
                "Define": "To irritate or annoy someone; to make a person very angry or bored; to infuriate. ",
                "Ex": "Her persistent nagging drove me up the wall. "
              },
              {
                "Idiom": "Fall off the back of a lorry",
                "Define": "A euphemism for something acquired illegally or stolen. ",
                "Ex": "He was trying to sell me a new laptop which I suspect fell off the back of a lorry. "
              },
              {
                "Idiom": "Fifth wheel",
                "Define": "Anything superfluous or unnecessary. ",
                "Ex": "I felt like a fifth wheel when they started looking at each other affectionately."
              },
              {
                "Idiom": "Highways and byways",
                "Define": "Major and minor roads. ",
                "Ex": "They spent their holiday exploring the highways and byways of the country "
              },
              {
                "Idiom": "Hit the road",
                "Define": "To begin traveling; to leave a place; to go away.",
                "Ex": "1. We've got a long way to go. Let's hit the road to make it by sunset. 2. It's time for me to hit the road; it is getting late."
              },
              {
                "Idiom": "Hitch one's wagon to a star",
                "Define": "Aspire to do something great or aim high, follow a great ambition. ",
                "Ex": "He urged his students to hitch their wagons to a star. "
              },
              {
                "Idiom": "In the same boat",
                "Define": "In the same situation; having the same problems. ",
                "Ex": "A: Can you lend me 100 dollars? B: Sorry, I am broke. I am in the same boat.      "
              },
              {
                "Idiom": "Itchy feet",
                "Define": "Feeling of a need to travel. ",
                "Ex": "She has itchy feet again. She says she will travel to Brazil. "
              },
              {
                "Idiom": "Jump on the bandwagon",
                "Define": "To profit from a craze; to join a trend. ",
                "Ex": "After the incredible success of the new product, the company has jumped on the bandwagon, and released a new version of it. "
              },
              {
                "Idiom": "Jump the lights",
                "Define": "To pass a set of traffic lights when they are not showing green.",
                "Ex": "It's dangerous to jump the lights. You may have a terrible accident. "
              },
              {
                "Idiom": "Make one's way",
                "Define": "To move in a particular direction; advance in life by one's own efforts. ",
                "Ex": "1. He made his way to the police station and told all about the murder. 2. He had to make his own way in the world as his family was very poor."
              },
              {
                "Idiom": "Miss the boat",
                "Define": "To fail to take advantage of an opportunity. ",
                "Ex": "The price discount ended yesterday and I just missed the boat on a great deal. "
              },
              {
                "Idiom": "My way or the highway",
                "Define": "This expression is used to say that people have to do what you say; otherwise, they will have to leave or quit the project. ",
                "Ex": "He has a My way or the highway approach to leading his government and his party."
              },
              {
                "Idiom": "Off the beaten track",
                "Define": "To a place or places not commonly visited. ",
                "Ex": "His trip was altogether off the beaten track which had never been traversed any European. "
              },
              {
                "Idiom": "On the wagon",
                "Define": "To abstain from drinking any alcoholic drink, usually in the sense of having given it up ",
                "Ex": "No, thank you! No alcohol for me, I am on the wagon."
              },
              {
                "Idiom": "Paddle one's own canoe",
                "Define": "To act independently and decide your own fate; to do something by oneself. ",
                "Ex": "He's been left to paddle his own canoe when he started his bussiness. "
              },
              {
                "Idiom": "Put the cart before the horse",
                "Define": "To put things in the wrong order. ",
                "Ex": "To attempt to remove the armaments before removing these substantive conflicts of interest is to put the cart before the horse. "
              },
              {
                "Idiom": "Put the pedal to the metal",
                "Define": "To press the gas pedal to the maximum extent; to exert maximum effort.",
                "Ex": "You have to put the pedal to the metal if you want to get there on time. "
              },
              {
                "Idiom": "Road rage",
                "Define": "Aggressive behavior exhibited by drivers in traffic, often as a result of stress. ",
                "Ex": "Many road accidents are the result of road rage. "
              },
              {
                "Idiom": "Sail through something",
                "Define": "To pass or progress quickly and easily. ",
                "Ex": "He sailed right through his homework. "
              },
              {
                "Idiom": "Take for a ride",
                "Define": "To deceive or cheat. ",
                "Ex": "It was only when he discovered that his wallet was gone that he realized they had taken him for a ride. "
              },
              {
                "Idiom": "Trip the light fantastic",
                "Define": "To dance. ",
                "Ex": "We were tripping the light fantastic all night. "
              },
              {
                "Idiom": "Wheels fall off",
                "Define": "Said about something that has failed, often after a laborious, tiring process. ",
                "Ex": "Our team was doing well for a while, but they got tired and then the wheels fell off. "
              }
            ],
            "War": [
              {
                "Idiom": "All's fair in love and war.",
                "Define": "In love or in war, you are allowed to be deceitful in order to get what you want. ",
                "Ex": "To get her to go out with him, he lied and told her that is very rich. All's fair in love and war. "
              },
              {
                "Idiom": "An act of war",
                "Define": "An act which is considered violent enough to cause war. ",
                "Ex": "Bombing the United States naval base at pearl harbor was considered an act of war."
              },
              {
                "Idiom": "Arrow in the quiver",
                "Define": "This idiom is used when talking about one of a number of resources or strategies that can be used to achieve a goal. ",
                "Ex": "If you are having a job interview, improving your communication skills can be another arrow in your quiver."
              },
              {
                "Idiom": "Bring a knife to a gunfight",
                "Define": "(Also, take a knife to a gunfight) To enter into a confrontation or other challenging situation without being adequately equipped or prepared. ",
                "Ex": "We lost the deal against much equipped competitors because we brought a knife to a gunfight. "
              },
              {
                "Idiom": "Call the shots",
                "Define": "If you call the shots you are in charge. You decide on the course of action and take the initiative.",
                "Ex": "This is my wedding party; I will call the shots. "
              },
              {
                "Idiom": "Cross swords",
                "Define": "To quarrel or argue with someone; to have a dispute with someone. ",
                "Ex": "The boss didn't want to cross swords with the workers' union. "
              },
              {
                "Idiom": "Double-edged sword",
                "Define": "A benefit that carries some significant but non-obvious cost or risk. ",
                "Ex": "Being a genius child is a double-edged sword because you cannot communicate with ordinary children. "
              },
              {
                "Idiom": "Draw fire",
                "Define": "If you draw fire, you attract hostile criticism.  ",
                "Ex": "His new book has drawn fire from many feminists. "
              },
              {
                "Idiom": "Drop a bombshell",
                "Define": "The phrase drop a bombshell refers to an alarming and unexpected announcement. ",
                "Ex": "His wife dropped a bombshell when she said she loved another man. "
              },
              {
                "Idiom": "Fight fire with fire",
                "Define": "If you fight fire with fire, you use the same methods and tactics that your opponent is using against you. Shakespeare referred to the same meaning in King John, 1595: Be stirring as the time; be fire with fire; threaten the threatener and outface the brow of bragging horror",
                "Ex": "After the competitive offers from rival firms, our company has decided to fight fire with fire and reduce prices. "
              },
              {
                "Idiom": "Go to war (over someone or something)",
                "Define": "To declare a war over someone or something. ",
                "Ex": "The US administration has gone to war over teenagers' pregnancy for decades in vain."
              },
              {
                "Idiom": "If you want peace, prepare for war",
                "Define": "The adage if you want peace, prepare for war means that if a country is well armed and is strong, its opponents will be less likely to attack it.       ",
                "Ex": "The general said that believing in disarmament is not a good idea and added: if you want peace, you must prepare for war. "
              },
              {
                "Idiom": "Keep your powder dry",
                "Define": "Be cautious and prepared for the worst. ",
                "Ex": "Trust in God, and keep your powder dry."
              },
              {
                "Idiom": "Lock and load",
                "Define": "The phrase lock and load means to prepare for an imminent event. This idioms comes from military jargon referring to the preparation of a weapon for battle. The phrase was used in 1949 by John Wayne in the movie Sands of Iwo Jima.    ",
                "Ex": "It's time to lock and load. "
              },
              {
                "Idiom": "Loose cannon",
                "Define": "The idiom loose cannon refers to a person who is unpredictable or uncontrolled and who is likely to cause unintentional damage. ",
                "Ex": "He is considered a loose cannon because he is unable to control himself."
              },
              {
                "Idiom": "Meet your Waterloo",
                "Define": "If someone meets their Waterloo they are defeated by someone who is stronger or by a problem that is very difficult to surmount. The phrase to meet one's Waterloo refers to the Battle of Waterloo near Belgium in 1815 where the French army under the command of Napoleon was defeated. The phrase entered the English language as a phrase signifying a great test with a final and decisive outcome - generally one resulting in failure and proving vincibility for something or someone who had seemed unbeatable.",
                "Ex": "He met his Waterloo when he was challenged by the young contestant. "
              },
              {
                "Idiom": "Ride shotgun",
                "Define": "To ride shotgun means to sit in the front passenger seat of a vehicle during a trip. Figuratively, the phrase refers to the support or aid given to someone in a situation or project. The expression riding shotgun is derived from shotgun messenger, a colloquial term for express messenger. A special armed employee of the express service using the stage for transportation of bullion or cash would sit beside the driver, carrying a short shotgun (or alternatively a rifle), to provide an armed response in case of threat to the cargo.",
                "Ex": "Would you like to come to the party and ride shotgun? We really need your help!"
              },
              {
                "Idiom": "Running battle",
                "Define": "The phrase running battle refers to an argument that continues over a long period of time.   ",
                "Ex": "He was fired because he had a running battle with his boss. "
              },
              {
                "Idiom": "Shot in the dark",
                "Define": "The phrase refers to a hopeful attempt at something or a wild guess especially when you have no certain information or knowledge about the subject. ",
                "Ex": "It was just a shot in the dark, but I was right! "
              },
              {
                "Idiom": "Stick to one's guns",
                "Define": "To stick to one's guns means to refuse to change one's convictions or beliefs.",
                "Ex": "His father wanted him to be a lawyer, but he stuck to his guns and followed a career as a writer. "
              },
              {
                "Idiom": "Take a stab at",
                "Define": "The phrase to take a stab at means to attempt or try.",
                "Ex": "I know the question is difficult to answer. Yet, I'd like to take a stab at answering it. "
              },
              {
                "Idiom": "To the hilt",
                "Define": "Completely, fully, to one's limit ",
                "Ex": "John has borrowed money from the bank to the hilt. "
              },
              {
                "Idiom": "War of nerves",
                "Define": "(Also battle of nerves.) War of nerves refers to a conflict using psychological techniques rather than direct violence in order to weaken the enemy. ",
                "Ex": "In the future, war will not merely be one of men and machines, it will be a war of wills and a war of nerves. Sir John Anderson. "
              },
              {
                "Idiom": "War of words",
                "Define": "An argument between two people or groups. ",
                "Ex": "The war of words between the two countries hasn't ceased for a long time."
              },
              {
                "Idiom": "War zone",
                "Define": "The idiom war zone refers to an area where war or some extreme violence is taking place. ",
                "Ex": "It is heart breaking to see images of dead or injured children from a war zone. "
              }
            ],
            "Weather": [
              {
                "Idiom": "Any port in a storm",
                "Define": "This idiom is used to describe a situation in which you are forced to accept any solution whether you like or not. ",
                "Ex": "I accepted the job although it was below my expectations. Any port in a storm, you know! "
              },
              {
                "Idiom": "Blue-sky thinking",
                "Define": "This refers to thinking that is unrealistic. This phrase may also refer to creative ideas that diverge from current beliefs or ideas. ",
                "Ex": "Blue-sky thinking has long been denigrated, and because of the economic recession, such fanciful thinking may even be considered downright irresponsible. "
              },
              {
                "Idiom": "Bolt from the blue",
                "Define": "This refers to a complete surprise; something totally unexpected. In this phrase there is an allusion to a stroke of lightning from a clear blue sky. ",
                "Ex": "The news that they are getting a divorce was a bolt from the blue. "
              },
              {
                "Idiom": "Chase rainbows",
                "Define": "This idiom is used when someone tries to pursue unrealistic or fanciful goals, things that are impossible. ",
                "Ex": "He thought he could convince the boss to appoint him as the new manager, but in fact he was chasing rainbows. "
              },
              {
                "Idiom": "Cloud nine",
                "Define": "This idiom is used to mean that you are in a state of extreme happiness. ",
                "Ex": "He was on cloud nine after he passed the exam. "
              },
              {
                "Idiom": "Come rain or shine",
                "Define": "(Also rain or shine) Regardless of the weather or circumstances. ",
                "Ex": "Don't worry! I'll be on time come rain or shine. "
              },
              {
                "Idiom": "Every cloud has a silver lining",
                "Define": "This expression is used to say that there is always something good even in an unpleasant, difficult or even painful situation. The origin of this expression is most likely traced to John Milton's Comus (1634) with the lines. Was I deceiv'd, or did a sable cloud? Turn forth her silver lining on the night ",
                "Ex": "You should never feel hopeless. Every cloud has a silver lining, you know "
              },
              {
                "Idiom": "Have a face like thunder",
                "Define": "This idiom is used to describe a person who is angry or upset about something. ",
                "Ex": "She had a face like thunder when she discovered the truth. "
              },
              {
                "Idiom": "In a fog",
                "Define": "(Also in a haze.) This idiom is used when someone is confused, dazed, disoriented.",
                "Ex": "After he heard the bad news, he was in a fog for a moment. "
              },
              {
                "Idiom": "In the cold light of day",
                "Define": "This idiom is used when you see things objectively, clearly and calmly, without the emotions you had at the time they occurred. ",
                "Ex": "Later, in the cold light of day, John realized his mistake. But it was too late; the harm was done. "
              },
              {
                "Idiom": "It never rains but it pours",
                "Define": "This expression is used to mean that things do not just happen occasionally, but all all at the same time. ",
                "Ex": "I woke up late, missed the bus and when I arrived to work I realized I lost my purse. It never rains but it pours. "
              },
              {
                "Idiom": "Lovely weather for ducks",
                "Define": "Rainy weather. ",
                "Ex": "A: What's the weather like there? B: It's a lovely weather for ducks.        "
              },
              {
                "Idiom": "Right as rain",
                "Define": "This idiom is used to mean that everything is perfectly fine; all right. ",
                "Ex": "Take these medicines and soon, you'll be right as rain. 2. She had had a lot of problem with her parents. But as soon as she got married, everything was right as rain for her.         "
              },
              {
                "Idiom": "Under a cloud",
                "Define": "This idiom is used to describe someone who is suspected of having done something wrong. ",
                "Ex": "After the murder of the old lady, everyone living in the house was under a cloud."
              },
              {
                "Idiom": "Under the weather",
                "Define": "Somewhat ill or gloomy. ",
                "Ex": "A: How have you been? B: I've been under the weather. But it's OK now.        "
              },
              {
                "Idiom": "Weather permitting",
                "Define": "If the weather is fine. ",
                "Ex": "Weather permitting, we will be able to go on a picnic tomorrow. "
              },
              {
                "Idiom": "Weather the storm",
                "Define": "To experience a very difficult situation and survive it. ",
                "Ex": "They lost everything they had, but somehow they weathered the storm. "
              }
            ],
            "Work": [
              {
                "Idiom": "A woman's work is never done",
                "Define": "The proverb a woman's work is never done means that a woman often works longer hours than a man because the housework and raising children are jobs that never end. The origin of the saying comes from an old rhymed couplet: Man may work from sun to sun, but woman's work is never done.",
                "Ex": "A woman's work is never done!, said Leila. She added: As soon as I finish washing the breakfast dishes, it's time to start preparing lunch. Then I have to go shopping and when the kids are back home I have to help them with their homework."
              },
              {
                "Idiom": "All in a day's work",
                "Define": "What is normal, typical or expected. ",
                "Ex": "Grading my students' papers is all on a day's work for me."
              },
              {
                "Idiom": "All work and no play makes Jack a dull boy",
                "Define": "All work and no play makes Jack a dull boy is a proverb which means that it is not good to work all the time and that people may get bored if they don't get some time off from work. This saying appeared first in James Howell's Proverbs in English, Italian, French and Spanish (1659), and was included in later collections of proverbs. Some writers have added a second part to the proverb:All work and no play makes Jack a dull boy. All play and no work makes Jack a mere toy.",
                "Ex": "I think you need to go out and have some fun. You know all work and no play makes Jack a dull boy."
              },
              {
                "Idiom": "Back to the salt mines",
                "Define": "If you go back to the salt mines, it means you have to return back to the workplace.   ",
                "Ex": "The vacation is over. Back to the salt mines!"
              },
              {
                "Idiom": "Bean counter",
                "Define": "An accountant. ",
                "Ex": "The company is hiring a new accountant."
              },
              {
                "Idiom": "Break your back",
                "Define": "If you break your back to do something, you work very hard to do it. ",
                "Ex": "I am not going to break my back to this job for such a low salary."
              },
              {
                "Idiom": "Burn candles at both ends",
                "Define": "If you burn candles at both ends, you work very hard, day and night.    ",
                "Ex": "She has been burning candles at both ends to finish a book about the history of the United States of America. "
              },
              {
                "Idiom": "Burn the midnight oil",
                "Define": "Work hard, especially late into the night. ",
                "Ex": "She was burning the midnight oil preparing for her daughter's wedding when she had a heart attack."
              },
              {
                "Idiom": "Cold piece of work",
                "Define": "If someone is a cold piece of work they are difficult to deal with. ",
                "Ex": "Did you see how she treats her husband? She is a cold piece of work. "
              },
              {
                "Idiom": "Devil finds work for idle hands to do",
                "Define": "People are inclined to do frivolous or harmful things to get rid of their boredom when they don't do anything useful. ",
                "Ex": "My husband made sure that the children are always occupied doing something because you know the devil finds work for idle hands to do."
              },
              {
                "Idiom": "Dirty work",
                "Define": "(Also do the dirty work) Unpleasant work or dishonest action. ",
                "Ex": "1. I don't know but I feel there is some dirty work going on in this company. 2. I always have to do the dirty work. I never have fun."
              },
              {
                "Idiom": "Do the dirty work",
                "Define": "The phrase do the dirty work means to do the disagreeable, illegal or dishonest things.   ",
                "Ex": "He always sends his assistant to do his dirty work rather than doing it himself. "
              },
              {
                "Idiom": "Get the sack",
                "Define": "To be dismissed from employment.",
                "Ex": "Because he was always late, he got the sack."
              },
              {
                "Idiom": "Gum up the works",
                "Define": "The phrase gum up the works means to prevent a process, a system or a machine from working smoothly.",
                "Ex": "He is not careful enough and always gums up the works. "
              },
              {
                "Idiom": "Keep up the good work",
                "Define": "The phrase keep up the good work is used to encourage a person to continue doing the good things they are doing now.",
                "Ex": "Well done! I couldn't have done it better myself. Keep up the good work."
              },
              {
                "Idiom": "Labor of love",
                "Define": "The phrase labor of love refers to a work that brings you great pleasure.",
                "Ex": "John helps street children get basic education as a labor of love. "
              },
              {
                "Idiom": "Shoot the works",
                "Define": "To spend all the money you have or to try as much as you can to do something. ",
                "Ex": "We shot the works on our son's education."
              },
              {
                "Idiom": "Sweat blood",
                "Define": "To work very hard. ",
                "Ex": "She sweats blood every day just to bring home the bacon."
              },
              {
                "Idiom": "Work like a beaver",
                "Define": "(Also work like a mule; work like a horse; work like a slave) To work like a beaver means to work very hard.   ",
                "Ex": "You work like a beaver; you need to relax."
              },
              {
                "Idiom": "Work like a charm",
                "Define": "If something works like a charm, it works works very well. The phrase contains the word charm which means a magic spell.",
                "Ex": "I installed the application on my cell phone and it works like a charm. "
              },
              {
                "Idiom": "Work your fingers to the bone",
                "Define": "To work extremely hard. ",
                "Ex": "He works his fingers to the bone to help his five children grow up in a healthy environment."
              }
            ],
            "Animal Idioms": [
              {
                "Idiom": "A bit much",
                "Define": "(Also Of undesirable behaviour) More than is reasonable; a bit too much.",
                "Ex": "1996  July 27, Tara Mack, “The Super Market Man; Johnny Johnson Is Selling Pride as Well as Groceries in Richmond”, Washington Post: He says sometimes people even ask him for his autograph. But that's a bit much, even for Johnny Johnson."
              },
              {
                "Idiom": "A cold day in Hell",
                "Define": "(Also slang) The time of occurrence of an event that will never happen.",
                "Ex": "It'll be a cold day in hell when that happens."
              },
              {
                "Idiom": "A cold day in July",
                "Define": "(Also colloquial) The time of occurrence of an event that will never happen.",
                "Ex": "It'll be a cold day in July when that happens."
              },
              {
                "Idiom": "A cut above",
                "Define": "(Followed by a noun phrase) Superior to.",
                "Ex": "Superior to the norm. In his speaking he is a cut above.He is a cut above the rest in his public speaking."
              },
              {
                "Idiom": "A cut below",
                "Define": "Inferior to; of a lower quality than",
                "Ex": "This waiter is really a cut below what I would expect from this restaurant."
              },
              {
                "Idiom": "A day late and a dollar short",
                "Define": "Action that was taken too late and is too feeble to be of any use.",
                "Ex": "I'm sorry but your business proposal is a day late and a dollar short.  You promised it two weeks ago and now we've already hired another firm."
              },
              {
                "Idiom": "A few sandwiches short of a picnic",
                "Define": "Exhibiting disquiet or unsoundness of mind; not sane; mad.",
                "Ex": "I think the lady down the road is a few sandwiches short of a picnic — you often hear strange bangings at odd hours in the morning."
              },
              {
                "Idiom": "a good deal",
                "Define": "Very much; to a great extent; a lot; lots.",
                "Ex": "We had a good deal more money afterwards.1865, Lewis Carroll, Alice's Adventures in Wonderland, Chapter 5: Advice from a Caterpillar,She was a good deal frightened by this very sudden change, but she felt that there was no time to be lost, as she was shrinking rapidly: so she set to work at once to eat some of the other bit."
              }
            ]
          },
          "keys": [
            "Age",
            "Animals",
            "Clothes",
            "Colors",
            "Crime",
            "Death",
            "Food",
            "Furniture",
            "General",
            "Health",
            "Home",
            "Law",
            "Life",
            "Love",
            "Men & Women",
            "Money",
            "Music",
            "Names",
            "Nature",
            "Numbers",
            "Parts of the body",
            "Relationship",
            "Religion",
            "Sexuality",
            "Sport",
            "Science",
            "Time",
            "Travel",
            "War",
            "Weather",
            "Work",
            "Animal Idioms"
          ]
        }

    """.trimIndent()

    lateinit var historyDao: HistoryDao
    lateinit var translationClient: TranslationClient
    lateinit var dictionaryClient: DictionaryClient
    lateinit var textToSpeechService: TextToSpeechService
    lateinit var speechToTextService: SpeechToTextService
}

fun String.getDrawableByName(): DrawableResource? {
    return when (this) {
        "ic_auto" -> Res.drawable.ic_auto
        "afrikaans" -> Res.drawable.afrikaans
        "albanian" -> Res.drawable.albanian
        "amharic" -> Res.drawable.amharic
        "arabic" -> Res.drawable.arabic
        "armenia" -> Res.drawable.armenia
        "assamese" -> Res.drawable.assamese
        "aymara" -> Res.drawable.aymara
        "azerbaijani" -> Res.drawable.azerbaijani
        "bambara" -> Res.drawable.bambara
        "basque" -> Res.drawable.basque
        "belarusian" -> Res.drawable.belarusian
        "bengali" -> Res.drawable.bengali
        "bhojpuri" -> Res.drawable.bhojpuri
        "bosnia" -> Res.drawable.bosnia
        "bulgaria" -> Res.drawable.bulgaria
        "myanmar" -> Res.drawable.myanmar
        "catalan" -> Res.drawable.catalan
        "cebuano" -> Res.drawable.cebuano
        "chichewa" -> Res.drawable.chichewa
        "chinese" -> Res.drawable.chinese
        "chinese" -> Res.drawable.chinese
        "corsican" -> Res.drawable.corsican
        "croatian" -> Res.drawable.croatian
        "czech" -> Res.drawable.czech
        "danish" -> Res.drawable.danish
        "dhivehi" -> Res.drawable.dhivehi
        "dogri" -> Res.drawable.dogri
        "dutch" -> Res.drawable.dutch
        "english" -> Res.drawable.english
        "esperanto" -> Res.drawable.esperanto
        "estonian" -> Res.drawable.estonian
        "ewe" -> Res.drawable.ewe
        "filipino" -> Res.drawable.filipino
        "finnish" -> Res.drawable.finnish
        "french" -> Res.drawable.french
        "frisian" -> Res.drawable.frisian
        "galician" -> Res.drawable.galician
        "georgia" -> Res.drawable.georgia
        "german" -> Res.drawable.german
        "greek" -> Res.drawable.greek
        "gujarati" -> Res.drawable.gujarati
        "haitian" -> Res.drawable.haitian
        "hausa" -> Res.drawable.hausa
        "hawaiian" -> Res.drawable.hawaiian
        "hebrew" -> Res.drawable.hebrew
        "hindi" -> Res.drawable.hindi
        "hmong" -> Res.drawable.hmong
        "hungary" -> Res.drawable.hungary
        "iceland" -> Res.drawable.iceland
        "igbo" -> Res.drawable.igbo
        "indonesian" -> Res.drawable.indonesian
        "irish" -> Res.drawable.irish
        "italian" -> Res.drawable.italian
        "japanese" -> Res.drawable.japanese
        "javanese" -> Res.drawable.javanese
        "kannada" -> Res.drawable.kannada
        "kazakh" -> Res.drawable.kazakh
        "khmer" -> Res.drawable.khmer
        "kinyarwanda" -> Res.drawable.kinyarwanda
        "konkani" -> Res.drawable.konkani
        "korean" -> Res.drawable.korean
        "kusdish" -> Res.drawable.kusdish
        "kyrgyz" -> Res.drawable.kyrgyz
        "lao" -> Res.drawable.lao
        "latin" -> Res.drawable.latin
        "latvian" -> Res.drawable.latvian
        "lithuanian" -> Res.drawable.lithuanian
        "lingala" -> Res.drawable.lingala
        "luxembourgish" -> Res.drawable.luxembourgish
        "northmacedonia" -> Res.drawable.northmacedonia
        "maithili" -> Res.drawable.maithili
        "malagasy" -> Res.drawable.malagasy
        "malay" -> Res.drawable.malay
        "malayalam" -> Res.drawable.malayalam
        "maltese" -> Res.drawable.maltese
        "maori" -> Res.drawable.maori
        "marathi" -> Res.drawable.marathi
        "mongolian" -> Res.drawable.mongolian
        "myanmar" -> Res.drawable.myanmar
        "nepali" -> Res.drawable.nepali
        "norwegian" -> Res.drawable.norwegian
        "nyanja" -> Res.drawable.nyanja
        "odia" -> Res.drawable.odia
        "oromo" -> Res.drawable.oromo
        "pashto" -> Res.drawable.pashto
        "persian" -> Res.drawable.persian
        "dari_persian" -> Res.drawable.dari_persian
        "polish" -> Res.drawable.polish
        "portuguese" -> Res.drawable.portuguese
        "punjabi" -> Res.drawable.punjabi
        "quechua" -> Res.drawable.quechua
        "romania" -> Res.drawable.romania
        "russian" -> Res.drawable.russian
        "samon" -> Res.drawable.samon
        "sanskrit" -> Res.drawable.sanskrit
        "scots_gaelic" -> Res.drawable.scots_gaelic
        "sepedi" -> Res.drawable.sepedi
        "serbian" -> Res.drawable.serbian
        "sesotho" -> Res.drawable.sesotho
        "shona" -> Res.drawable.shona
        "sindhi" -> Res.drawable.sindhi
        "sinhala" -> Res.drawable.sinhala
        "slovak" -> Res.drawable.slovak
        "slovenia" -> Res.drawable.slovenia
        "somali" -> Res.drawable.somali
        "spanish" -> Res.drawable.spanish
        "sundanese" -> Res.drawable.sundanese
        "swahili" -> Res.drawable.swahili
        "sweden" -> Res.drawable.sweden
        "tajik" -> Res.drawable.tajik
        "tamil" -> Res.drawable.tamil
        "tatar" -> Res.drawable.tatar
        "telugu" -> Res.drawable.telugu
        "thai" -> Res.drawable.thai
        "tigrinya" -> Res.drawable.tigrinya
        "tsonga" -> Res.drawable.tsonga
        "turkish" -> Res.drawable.turkish
        "turkmen" -> Res.drawable.turkmen
        "ukrainian" -> Res.drawable.ukrainian
        "urdu" -> Res.drawable.urdu
        "uyghur" -> Res.drawable.uyghur
        "uzbek" -> Res.drawable.uzbek
        "vietnam" -> Res.drawable.vietnam
        "welsh" -> Res.drawable.welsh
        "xhosa" -> Res.drawable.xhosa
        "yiddish" -> Res.drawable.yiddish
        "yoyuba" -> Res.drawable.yoyuba
        "zulu" -> Res.drawable.zulu
        else -> null
    }
}

fun getQuotesJson() = """
    [
      {
        "name": "Albert Einstein",
        "dates": "14 Mar 1879 - 18 Apr 1955",
        "quotes": [
          "I have no special talent. I am only passionately curious.",
          "Imagination is more important than knowledge.Knowledge is limited.Imagination encircles the world.",
          "All religions, arts and sciences are branches of the same tree.",
          "I speak to everyone in the same way, whether he is the garbage man or the president of the university.",
          "The great moral teachers of humanity were, in a way, artistic geniuses in the art of living.",
          "Imagination is the highest form of research.",
          "If you want your children to be intelligent, read them fairy tales. If you want them to be more intelligent, read them more fairy tales.",
          "The search for truth is more precious than its possession.",
          "Intellectuals solve problems, geniuses prevent them.",
          "Coincidence is God's way of remaining anonymous."
        ]
      },
      {
        "name": "William Shakespeare",
        "dates": "26 Apr 1564 - 23 Apr 1616",
        "quotes": [
          "God has given you one face, and you make yourself another.",
          "It is not in the stars to hold our destiny but in ourselves.",
          "Ignorance is the curse of God, knowledge is the wing wherewith we fly to heaven.",
          "Better three hours too soon than a minute too late.",
          "Listen to many, speak to a few.",
          "Women may fall when there’s no strength in men.",
          "There is nothing either good or bad, but thinking makes it so.",
          "Hell is empty and all the devils are here.",
          "We know what we are, but not what we may be.",
          "You speak an infinite deal of nothing."
        ]
      },
      {
        "name": "Mother Teresa",
        "dates": "26 Aug 1910 - 5 Sep 1997",
        "quotes": [
          "If you judge people, you have no time to love them.",
          "If you can’t feed a hundred people, then feed just one.",
          "Love begins at home, and it is not how much we do… but how much love we put in that action.",
          "Yesterday is gone. Tomorrow has not yet come. We have only today. Let us begin.",
          "Kind words can be short and easy to speak, but their echoes are truly endless.",
          "If we really want to love, we must learn how to forgive.",
          "Every time you smile at someone, it is an action of love, a gift to that person, a beautiful thing.",
          "The most terrible poverty is loneliness, and the feeling of being unloved.",
          "What can you do to promote world peace? Go home and love your family."
        ]
      },
      {
        "name": "Steve Jobs",
        "dates": "24 Feb 1955 - 5 Oct 2011",
        "quotes": [
          "Sometimes life is going to hit you in the head with a brick. Don’t lose faith.",
          "Sometimes when you innovate, you make mistakes. It is best to admit them quickly and get on with improving your other innovations.",
          "Innovation distinguishes between a leader and a follower.",
          "I think if you do something and it turns out pretty good, then you should go do something else wonderful, not dwell on it for too long. Just figure out what’s next.",
          "Quality is more important than quantity. One home run is much better than two doubles.",
          "The people who are crazy enough to think they can change the world are the ones who do.",
          "Details matter, it’s worth waiting to get it right.",
          "Get closer than ever to your customers. So close that you tell them what they need well before they realize it themselves.",
          "You have to believe that the dots will somehow connect in your future.",
          "If you don’t love it, you’re going to fail."
        ]
      },
      {
        "name": "Bruce Lee",
        "dates": "27 Nov 1940 - 20Jul 1973",
        "quotes": [
          "If you spend too much time thinking about a thing, you’ll never get it done.",
          "Do not pray for an easy life, pray for the strength to endure a difficult one.",
          "Mistakes are always forgivable, if one has the courage to admit them.",
          "Real living is living for others.",
          "Showing off is the fool’s idea of glory.",
          "Defeat is a state of mind, No one is ever defeated until defeat has been accepted as a reality.",
          "Never waste energy on worries or negative thoughts, all problems are brought into existence– drop them.",
          "A wise man can learn more from a foolish question than a fool can learn from a wise answer.",
          "Always be yourself, express yourself, have faith in yourself, do not go out and look for a successful personality and duplicate it.",
          "If you don’t want to slip up tomorrow, speak the truth today."
        ]
      },
      {
        "name": "Nelson Mandela",
        "dates": "18 Jul 1918 - 5 Dec 2013",
        "quotes": [
          "Real leaders must be ready to sacrifice all for the freedom of their people.",
          "Education is the most powerful weapon which you can use to change the world.",
          "Do not judge me by my successes, judge me by how many times I fell down and got back up again.",
          "Money won't create success, the freedom to make it will.",
          "I am the master of my fate: I am the captain of my soul.",
          "A winner is a dreamer who never gives up.",
          "Live life as though nobody is watching, and express yourself as though everyone is listening.",
          "One of the most difficult things is not to change society — but to change yourself.",
          "If you want to make peace with your enemy, you have to work with your enemy. Then he becomes your partner.",
          "It always seems impossible until it’s done."
        ]
      },
      {
        "name": "Muhammad Ali Jinnah",
        "dates": "25 Dec 1876 - 11 Sep 1948",
        "quotes": [
          "Failure is a word unknown to me.",
          "Expect the best, prepare for the worst.",
          "I do not believe in taking the right decision, I take a decision and make it right.",
          "No nation can rise to the height of glory unless your women are side by side with you.",
          "Think a hundred times before you take a decision, but once that decision is taken, stand by it as one man.",
          "I insist you to strive. Work, Work and only work for satisfaction with patience, humbleness and serve thy nation.",
          "There is no power on earth that can undo Pakistan.",
          "That freedom can never be attained by a nation without suffering and sacrifice has been amply borne out by the recent tragic happenings in this subcontinent.",
          "Religion should not be allowed to come into Politics…Religion is merely a matter between man and God."
        ]
      },
      {
        "name": "Mahatma Gandhi",
        "dates": "2 Oct 1869 - 30 Jan 1948",
        "quotes": [
          "A man is but a product of his thoughts. What he thinks he becomes.",
          "Nobody can hurt me without my permission.",
          "Happiness is when what you think, what you say, and what you do are in harmony.",
          "The weak can never forgive. Forgiveness is an attribute of the strong.",
          "Live as if you were to die tomorrow. Learn as if you were to live forever.",
          "The future depends on what you do today.",
          "It’s easy to stand in the crowd but it takes courage to stand alone.",
          "Strength does not come from physical capacity. It comes from an indomitable will.",
          "We need not wait to see what others do.",
          "The best way to find yourself is to lose yourself in the service of others."
        ]
      },
      {
        "name": "Martin Luther King Jr.",
        "dates": "15 Jan 1929 - 4 Apr 1968",
        "quotes": [
          "If you can't fly then run, if you can't run then walk, if you can't walk then crawl, but whatever you do you have to keep moving forward.",
          "A man who won't die for something is not fit to live.",
          "No person has the right to rain on your dreams.",
          "Intelligence plus character--that is the goal of true education.",
          "Nothing in the world is more dangerous than sincere ignorance and conscientious stupidity.",
          "There comes a time when silence is betrayal.",
          "We must use time creatively, in the knowledge that the time is always ripe to do right.",
          "A lie cannot live.",
          "There can be no deep disappointment where there is not deep love.",
          "It is not enough to say we must not wage war. It is necessary to love peace and sacrifice for it."
        ]
      },
      {
        "name": "Abraham Lincoln",
        "dates": "12 Frb 1809 - 15 Apr 1865",
        "quotes": [
          "Whatever you are, be a good one.",
          "I am a slow walker, but I never walk back.",
          "The best way to predict your future is to create it.",
          "I have always found that mercy bears richer fruits than strict justice.",
          "When I do good, I feel good. When I do bad, I feel bad. That’s my religion.",
          "All I have learned, I learned from books.",
          "Love is the chain to lock a child to its parent.",
          "No man has a good enough memory to be a successful liar.",
          "If I were two-faced, would I be wearing this one?",
          "Folks are usually about as happy as they make their minds up to be."
        ]
      },
      {
        "name": "Pablo Picasso",
        "dates": "25 Oct 1881 - 8 Apr 1973",
        "quotes": [
          "Every child is an artist. The problem is to remain an artist once they grow up.",
          "It takes a long time to become young.",
          "Action is the foundational key to all success.",
          "Everything is a miracle. It is a miracle that one does not dissolve in one’s bath like a lump of sugar.",
          "Inspiration exists, but it has to find you working.",
          "Every act of creation is first an act of destruction.",
          "Youth has no age.",
          "Bad artists copy. Good artists steal.",
          "If only we could pull out our brain and use only our eyes."
        ]
      },
      {
        "name": "Walt Disney",
        "dates": "5 Dec 1901 - 15 Dec 1966",
        "quotes": [
          "All our dreams can come true, if we have the courage to pursue them.",
          "When you’re curious, you find lots of interesting things to do.",
          "A man should never neglect his family for business.",
          "Our greatest natural resource is the minds of our children.",
          "Why worry? If you’ve done the very best you can, then worrying won’t make it any better.",
          "First, think. Second, dream. Third, believe. And finally, dare.",
          "Laughter is America’s most important export.",
          "Fantasy and reality often overlap.",
          "You reach a point where you don’t work for money.",
          "The way to get started is to quit talking and begin doing."
        ]
      },
      {
        "name": "Elon Musk",
        "dates": "28 Jun 1971 - Present",
        "quotes": [
          "If you get up in the morning and think the future is going to be better, it is a bright day. Otherwise, it's not.",
          "I think it is possible for ordinary people to choose to be extraordinary.",
          "Failure is an option here. If things are not failing, you are not innovating enough.",
          "Patience is a virtue, and I’m learning patience. It’s a tough lesson.",
          "If something’s important enough, you should try. Even if the probable outcome is failure.",
          "When I was in college, I wanted to be involved in things that would change the world. Now I am.",
          "I don’t create companies for the sake of creating companies, but to get things done.",
          "Being an entrepreneur is like eating glass and staring into the abyss of death.",
          "I don’t think it’s a good idea to plan to sell a company.",
          "Persistence is very important. You should not give up unless you are forced to give up."
        ]
      },
      {
        "name": "Florence Nightingale",
        "dates": "12 May 1820 - 13 Aug 1910",
        "quotes": [
          "There is no part of my life, upon which I can look back without pain.",
          "Everything is sketchy. The world does nothing but sketch.",
          "That Religion is not devotion, but work and suffering for the love of God, this is the true doctrine of Mystics.",
          "For the sick it is important to have the best.",
          "How very little can be done under the spirit of fear.",
          "So never lose an opportunity of urging a practical beginning, however small, for it is wonderful how often in such matters the mustard-seed germinates and roots itself.",
          "I attribute my success to this – I never gave or took any excuse.",
          "Let us never consider ourselves finished nurses. We must be learning all of our lives.",
          "Nursing is a progressive art such that to stand still is to go backwards."
        ]
      },
      {
        "name": "Mark Twain",
        "dates": "30 Nov 1835 - 21 Apr 1910",
        "quotes": [
          "If you tell the truth, you don’t have to remember anything.",
          "Whenever you find yourself on the side of the majority, it is time to reform (or pause and reflect).",
          "A lie can travel half way around the world while the truth is putting on its shoes.",
          "Never allow someone to be your priority while allowing yourself to be their option.",
          "Always do what is right. It will gratify half of mankind and astound the other.",
          "The two most important days in your life are the day you are born and the day you find out why.",
          "Better to keep your mouth shut and appear stupid than to open it and remove all doubt.",
          "Good friends, good books, and a sleepy conscience: this is the ideal life.",
          "Kindness is the language which the deaf can hear and the blind can see.",
          "Always obey your parents when they are present."
        ]
      },
      {
        "name": "Maya Angelou",
        "dates": "4 Apr 1928 - 28 May 2014",
        "quotes": [
          "If you don’t like something, change it. If you can’t change it, change your attitude.",
          "We may encounter many defeats but we must not be defeated.",
          "A wise woman wishes to be no one’s enemy, a wise woman refuses to be anyone’s victim.",
          "All great achievements require time.",
          "I believe that every person is born with talent.",
          "I believe that each of us comes from the Creator trailing wisps of glory.",
          "It's one of the greatest gifts you can give yourself: to forgive. Forgive everybody.",
          "People will forget what you said, people will forget what you did, but people will never forget how you made them feel.",
          "Do the best you can until you know better. Then when you know better, do better.",
          "Try to be a rainbow in someone’s cloud."
        ]
      },
      {
        "name": "Paul Walker",
        "dates": "12 Sep 1973 - 30 Nov 2013",
        "quotes": [
          "My philosophy is: If you can’t have fun, there’s no sense in doing it.",
          "I like a woman that’s capable and at the same time feminine. I admire tomboy qualities but with a feminine touch.",
          "There’s a time and place for everything, but as I get older, I like finding those human moments and really connecting.",
          "I’m one of those people that think certain things happen at certain times for all the right reasons.",
          "Sometimes the hurdles aren’t really hurdles at all. They’re welcome challenges, tests.",
          "I don’t care what it is in life: listen to your heart. If you do, no matter what, you win.",
          "I'm a crazy car guy. I've got an airplane hangar full of cars.",
          "I'm not afraid of anything. That's just the way I am.",
          "If you spend any time with a man, you'll realize that we're all still little boys.",
          "The whole thought of being a dad was scary to me."
        ]
      },
      {
        "name": "Vincent Van Gogh",
        "dates": "30 Mar 1853 - 29 Jul 1890",
        "quotes": [
          "I put my heart and my soul into my work, and have lost my mind in the process.",
          "I am still far from being what I want to be, but with God's help I shall succeed.",
          "Painting is a faith, and it imposes the duty to disregard public opinion.",
          "The best way to know God is to love many things.",
          "If you hear a voice within you say you cannot paint, then by all means paint, and that voice will be silenced.",
          "I often think that the night is more alive and more richly colored than the day.",
          "Drawing is the root of everything, and the time spent on that is actually all profit.",
          "I dream of painting and then I paint my dream.",
          "There is no blue without yellow and without orange.",
          "Great things are not done by impulse, but by a series of small things brought together."
        ]
      },
      {
        "name": "Leo Tolstoy",
        "dates": "28 Aug 1828 - 20 Nov 1910",
        "quotes": [
          "Wrong does not cease to be wrong because the majority share in it.",
          "Truth, like gold, is to be obtained not by its growth, but by washing away from it all that is not gold.",
          "Everyone thinks of changing the world, but no one thinks of changing himself.",
          "Respect was invented to cover the empty place where love should be.",
          "It is amazing how complete is the delusion that beauty is goodness.",
          "If you look for perfection, you’ll never be content.",
          "All, everything that I understand, I understand only because I love.",
          "The only thing that we know is that we know nothing and that is the highest flight of human wisdom.",
          "The sole meaning of life is to serve humanity.",
          "A truly wise man is always joyful."
        ]
      },
      {
        "name": "Walt Whitman",
        "dates": "31 May 1819 - 26 Mar 1892",
        "quotes": [
          "Simplicity is the glory of expression.",
          "Happiness, not in another place but this place…not for another hour, but this hour.",
          "Peace is always beautiful.",
          "The future is no more uncertain than the present.",
          "Every hour of every day is an unspeakably perfect miracle.",
          "All beauty comes from beautiful blood and a beautiful brain.",
          "Keep your face always toward the sunshine - and shadows will fall behind you.",
          "Either define the moment or the moment will define you.",
          "The truth is simple. If it was complicated, everyone would understand it.",
          "Be curious, not judgmental."
        ]
      },
      {
        "name": "Eleanor Roosevelt",
        "dates": "11 Oct 1884 - 7 Nov 1962",
        "quotes": [
          "People grow through experience if they meet life honestly and courageously.",
          "Great minds discuss ideas, average minds discuss events, small minds discuss people.",
          "It is better to light a candle than curse the darkness.",
          "Friendship with one’s self is all important, because without it one can not be friends with anyone else in the world.",
          "A woman is like a tea bag, you never know how strong it is until it’s in hot water.",
          "No one can make you feel inferior without your consent.",
          "Tomorrow is a mystery. Today is a gift. That is why it is called the present.",
          "Life is what you make it. Always has been, always will be.",
          "Character building begins in our infancy and continues until death.",
          "Nothing has ever been achieved by the person who says, ‘It can’t be done.'"
        ]
      },
      {
        "name": "Dalai Lama",
        "dates": "6 Jul 1935 - Present",
        "quotes": [
          "If you think you are too small to make a difference, try sleeping with a mosquito.",
          "Happiness is not something ready made. It comes from your own actions.",
          "Love is the absence of judgement.",
          "Silence is sometimes the best answer.",
          "Know the rules well, so you can break them effectively.",
          "Choose to be optimistic, it feels better.",
          "An open heart is an open mind.",
          "We can never obtain peace in the outer world until we make peace with ourselves.",
          "An eye for an eye….we are all blind.",
          "In our struggle for freedom, truth is the only weapon we possess."
        ]
      },
      {
        "name": "Oprah Winfrey",
        "dates": "29 Jan 1954 - Present",
        "quotes": [
          "When you undervalue what you do, the world will undervalue who you are.",
          "Doing the best at this moment puts you in the best place for the next moment.",
          "Turn your wounds into wisdom.",
          "You can have it all. Just not all at once.",
          "Real integrity is doing the right thing, knowing that nobody’s going to know whether you did it or not.",
          "One of the hardest things in life to learn are which bridges to cross and which bridges to burn.",
          "Create the highest, grandest vision possible for your life, because you become what you believe.",
          "If you are still breathing, you have a second chance.",
          "The miracle of your existence calls for celebration every day.",
          "The greatest lesson of life is that you are responsible for your life."
        ]
      },
      {
        "name": "Henry Ford",
        "dates": "30 Jul 1863 - 7 Apr 1947",
        "quotes": [
          "Whether you think you can, or you think you can’t–you’re right.",
          "Anyone who stops learning is old, whether at twenty or eighty. Anyone who keeps learning stays young.",
          "You can’t build a reputation on what you are going to do.",
          "Failure is only the opportunity more intelligently to begin again.",
          "Don’t find fault, find a remedy, anybody can complain.",
          "The only real mistake is the one from which we learn nothing.",
          "You don’t have to hold a position in order to be a leader.",
          "Quality means doing it right when no one is looking.",
          "As we advance in life we learn the limits of our abilities.",
          "If I had asked people what they wanted, they would have said ‘faster horses."
        ]
      },
      {
        "name": "Farrah Gray",
        "dates": "9 Sep 1984 - Present",
        "quotes": [
          "Never duck responsibility, its like running from the rain only to fall into the river.",
          "The more we give, the more we receive. It's important to give back, because the seeds you plant today, you will harvest tomorrow.",
          "Money doesn't change who you are, it magnifies who you really are.",
          "Always define your area of excellence. Establish the area where you will be the best.",
          "Comfort is the enemy of achievement.",
          "Keep putting out good. It will come back to you tenfold in unexpected ways.",
          "Build your own dreams, or someone else will hire you to build theirs.",
          "A goal is a dream with a deadline.",
          "Don’t be pushed by your problems. Be led by your dreams.",
          "It's the possibility of having a dream come true that makes life interesting."
        ]
      }
    ]
""".trimIndent()

fun getQuoteIcon(key: String): DrawableResource? {
    val map = hashMapOf(
        "Albert Einstein" to Res.drawable.alberteinstein,
        "William Shakespeare" to Res.drawable.williamshakespeare,
        "Mother Teresa" to Res.drawable.motherteresa,
        "Steve Jobs" to Res.drawable.stevejobs,
        "Bruce Lee" to Res.drawable.brucelee,
        "Nelson Mandela" to Res.drawable.nelsonmandela,
        "Muhammad Ali Jinnah" to Res.drawable.malijinnah,
        "Mahatma Gandhi" to Res.drawable.mahatmaghandi,
        "Martin Luther King Jr." to Res.drawable.michaelkingjr,
        "Abraham Lincoln" to Res.drawable.abrahamlincoln,
        "Pablo Picasso" to Res.drawable.pablopicasso,
        "Walt Disney" to Res.drawable.waltdisney,
        "Elon Musk" to Res.drawable.elonmusk,
        "Florence Nightingale" to Res.drawable.florencenightingale,
        "Mark Twain" to Res.drawable.marktwain,
        "Maya Angelou" to Res.drawable.mayaangelou,
        "Paul Walker" to Res.drawable.paulwalker,
        "Vincent Van Gogh" to Res.drawable.vincentvangog,
        "Leo Tolstoy" to Res.drawable.levtolstoy,
        "Walt Whitman" to Res.drawable.waltwhitman,
        "Eleanor Roosevelt" to Res.drawable.eleanorroosevelt,
        "Dalai Lama" to Res.drawable.dalailama,
        "Oprah Winfrey" to Res.drawable.oprahwinfrey,
        "Henry Ford" to Res.drawable.henryford,
        "Farrah Gray" to Res.drawable.grayfarrah,
    )
    return map[key]
}