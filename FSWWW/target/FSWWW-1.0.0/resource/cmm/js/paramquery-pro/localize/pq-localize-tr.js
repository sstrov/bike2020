/**
 * @author Ömer Osmanoğlu
 */
if("function"==typeof require)
    var jQuery = jQuery || require("jquery");

(function( $ ){

var pq = $.paramquery, 
    local='tr',
    grid = pq.pqGrid.regional[local] = {
        strLocal: local,
        strAdd:  "Ekle",
        strBlanks:  "(Boş)",
        strDelete:  "Sil",
        strEdit:  "Düzenle",
        strCondition:  "Koşul:",
        strConditions:  {
            "":  "--Seçim--",
            begin:  "Başlayan",
            between:  "Arasında",
            contain:  "İçeren",
            equal:  "Eşit",
            empty:  "Boş",
            end:  "Biten",
            great:  "Büyük",
            gte:  "Büyük veya Eşit",
            less:  "Küçük",
            lte:  "Küçük veya eşit",
            notbegin:  "Başlamayan",
            notcontain:  "İçermeyen",
            notequal:  "Eşit Olmayan",
            notempty:  "Boş Olmayan",
            notend:  "Bitmeyen",
            range:  "[Dizi]",
            regexp:  "Koşullu İfade"
        },
        strOk:  'Tamam',
        strClear:  'Temiz',
        strGroup_header:  "Gruplamak istediğiniz sütünu buraya sürükleyin.",
        strGroup_merge:  'Hücreleri Birleştir',
        strGroup_fixCols:  'Sütunları Sabitle',
        strGroup_grandSummary:  'Genel Toplam',
        strTP_aggPane:  "Toplama",
        strTP_colPane:  'Sütun Grupları',
        strTP_pivot:  "Özet Tablo",
        strTP_rowPane:  "Satır Grupları",
        strTP_aggPH:  "Toplamasını almak istediğiniz sütunları bırakın",
        strTP_colPH:  "X ekseni boyunca sütunları gruplamak için bırakın",
        strTP_rowPH:  "Y ekseni boyunca sütunları gruplamak için bırakın",
        strLoading:  "Yükleniyor",
        strNextResult:  "Sonraki Sonuç",
        strNoRows:  "Görüntülenecek veri yok",
        strNothingFound:  "Birşey Bulunamadı.",
        strPrevResult:  "Önceki Sonuç",
        strSearch:  "Ara",
        strSelectAll:  "Hepsini Seç",
        strSelectedmatches:  "{0} kayıttan {1} eşleşme",
        strFormulas: {
            ABS:  ["ABS(sayı)","Bir sayının mutlak değerini döndürür. Bir sayının mutlak değeri, işaretsiz sayıdır."],
            ACOS:  ["ACOS(sayı)","Bir sayının arkosin veya ters kosinüsünü döndürür. Arkosin, kosinüsü sayı olan açıdır. Dönüş açısı radyanlarda 0 (sıfır) ila pi arasında verilir."],
            AND:  ["AND(Koşul1, [Koşul2], ...)","Tüm argümanları DOĞRU olarak değerlendirirse DOĞRU değerini döndürür ve bir veya daha fazla argüman YANLIŞ olarak değerlendirirse YANLIŞ değerini döndürür."],
            ASIN:  ["ASIN(sayı)","Bir sayının arksini veya ters sinüsünü döndürür. Arksin sinüs sayısı olan açıdır. Dönüş açısı, -pi / 2 ila pi / 2 aralığında radyan olarak verilir."],
            ATAN:  ["ATAN(sayı)","Bir sayının arktanjantı ve ters teğet tanesini döndürür. Bu arktanent, teğet sayısı olan açıdır. Dönüş açısı, -pi / 2 ila pi / 2 aralığında radyan olarak verilir."],
            AVERAGE:  ["AVERAGE(sayı1, [sayı2], ...)","Bağımsız değişkenlerin ortalama (aritmetik ortalaması) değerini döndürür. Örneğin, A1: A20 aralığı sayı içeriyorsa, = AVERAGE(A1: A20) formülü bu sayıların ortalamasını döndürür."],
            AVERAGEIF:  ["AVERAGEIF(aralık, kriter, [Ortalama_Aralık])","Belirli bir kriteri karşılayan bir aralıktaki tüm hücrelerin ortalamasını (aritmetik ortalama) döndürür."],
            AVERAGEIFS:  ["AVERAGEIFS(Ortalama Aralık, Kriter Aralığı-1, Kriter-1, [Kriter Aralığı-2, Kriter-2], ...)","Birden çok ölçütü karşılayan tüm hücrelerin ortalamasını (aritmetik ortalama) döndürür."],
            CEILING:  ["CEILING(sayı, hane)","Sayı, sıfırdan uzak, en yakın çoklu değere yuvarlanır. Örneğin, fiyatlarınızda kuruş kullanmaktan kaçınmak istiyorsanız ve ürününüz 4.42 TL 'a kadar fiyatlandırılıyorsa, = CEILING (4.42,0.05) formülünü kullanarak en yakın nikele kadar fiyatlara ulaşın."],
            CHAR:  ["CHAR(sayı)","Bir sayı ile belirtilen karakteri döndürür."],
            CHOOSE:  ["CHOOSE(Sıra No, değer1, [değer2], ...)","Dizin numarası 1 ise, CHOOSE değer1 değerini döndürür; 2 ise, CHOOSE değer2 değerini döndürür; ve bunun gibi."],
            CODE:  ["CODE(metin)","Bir metin dizesindeki ilk karakter için sayısal bir kod döndürür."],
            COLUMN:  ["COLUMN()","COLUMN işlevinin göründüğü hücrenin başvurusunu döndürür."],
            COLUMNS:  ["COLUMNS(dizi)","Bir dizideki veya referanstaki sütun sayısını döndürür."],
            CONCATENATE:  ["CONCATENATE(metin-1, [metin-2], ...)","Bir dizeye iki veya daha fazla metin dizesi ekleyin."],
            COS:  ["COS(sayı)","Verilen açının kosinüsünü (radyan cinsinden) döndürür."],
            COUNT:  ["COUNT(sayı1-, [sayı-2], ...)","Sayıları içeren hücre sayısını sayar ve bağımsız değişkenler listesindeki sayıları sayar."],
            COUNTA:  ["COUNTA(değer-1, [değer-2], ...)","COUNTA işlevi, aralıkta boş olmayan hücre sayısını sayar.."],
            COUNTBLANK:  ["COUNTBLANK(aralık)","Belirtilen hücre aralığındaki boş hücreleri sayar."],
            COUNTIF:  ["COUNTIF(aralık, kriter)","Bir kriteri karşılayan hücre sayısını sayar; Örneğin, belirli bir şehrin müşteri listesinde görünme sayısını saymak için.."],
            COUNTIFS:  ["COUNTIFS(Kriter aralığı-1, Kriter-1, [Kriter_aralığı-2, Kriter-2]…)","Birden çok aralıktaki hücrelere ölçütler uygular ve tüm ölçütlerin karşılanma sayısını sayar."],
            DATE:  ["DATE(Yıl,Ay,Gün)","Belirli bir tarihi temsil eden sıralı seri numarasını döndürür."],
            DATEDIF:  ["DATEDIF(Başlangıç Tarihi,Bitiş Tarihi,İstenen Öğe)","İki tarih arasındaki gün, ay veya yıl sayısını hesaplar. Sonuç 'Y', 'M' veya 'D' olabilir."],
            DATEVALUE:  ["DATEVALUE(Tarih)", "Metin olarak saklanan bir tarihi, Excel'in tarih olarak tanıdığı bir seri numaraya dönüştürür. Örneğin, = DATEVALUE (\"1/1/2008\") formülü 39448 değerini döndürür."],
            DAY:  ["DAY(Gün Numarası)","Bir seri numarası ile temsil edilen bir tarihin gününü döndürür."],
            DAYS:  ["DAYS(Bitiş Tarihi, Başlangıç Tarihi)","İki tarih arasındaki gün sayısını göndürür."],
            DEGREES:  ["DEGREES(açı)","Radyanları dereceye dönüştürür."],
            EOMONTH:  ["EOMONTH(Başlangıç Tarihi, Aylar)","Başlangıç_tarihi, başlangıç_tarihi başlama tarihinden önceki veya sonraki ayın son günü olan seri numarasını döndürür."],
            EXP:  ["EXP(sayı)","Sayı gücüne yükseltilmiş e döndürür."],
            FIND:  ["FIND(Aranacak Metin, Metin, [Başlangıç No])","Bir metin dizesini ikinci bir metin dizesi içinde bulur ve ilk metin dizesinin başlangıç konumunun numarasını ikinci metin dizesinin ilk karakterinden döndürür.."],
            FLOOR:  ["FLOOR(sayı, önem)","Sayı aşağı doğru, sıfıra doğru en yakın katsayıya çevirir."],
            HLOOKUP:  ["HLOOKUP(Arama Değeri, Tablo Dizisi, Satır Dizisi, [Aralık Araması])","Bir tablonun üst satırında veya bir değer dizisinde bir değer arar ve ardından tablo veya dizide belirttiğiniz satırdaki aynı sütunda bir değer döndürür."],
            HOUR:  ["HOUR(saat)","Bir zaman değerinin saatini döndürür. Saat, 0 (12:00 Ö.Ö.) ila 23 (11:00 Ö.S) arasında değişen bir tamsayı olarak verilir."],
            IF:  ["IF(mantıksal test, doğru ise değer, [yanlış ise])","Bir koşul doğruysa ve yanlışsa başka bir değer varsa bir değer döndürür."],
            //IFERROR:  ["IFERROR(değer, Hata durumunda gösterilecek)","Bir formül bir hatayı değerlendirirse belirttiğiniz bir değeri döndürür; aksi halde, formülün sonucunu döndürür. Formüldeki hataları yakalamak ve işlemek için IFERROR işlevini kullanın.."],
            INDEX:  ["INDEX(dizi, satır numarası, [sütun numarası])","Satır ve sütun numarası dizinleriyle seçilen bir tablodaki veya bir dizideki öğenin değerini döndürür."],
            INDIRECT:  ["INDIRECT(Ref.Metni, [a1])","Bir metin dizesiyle belirtilen referansı döndürür. Referanslar içeriğini görüntülemek için hemen değerlendirilir."],
            LARGE:  ["LARGE(dizi, k)","Veri kümesindeki k-th en büyük değerini döndürür."],
            LEFT:  ["LEFT(metin, [karakter sayısı])","Belirttiğiniz karakter sayısına bağlı olarak bir metin dizesindeki ilk karakteri veya karakterleri döndürür.."],
            LEN:  ["LEN(metin)","Bir metin dizesindeki karakter sayısını döndürür."],
            LOOKUP: ["LOOKUP(arama değeri, arama vektörü, [sonuç vektör])","Bir değer için tek satırlı veya tek sütunlu bir aralıkta (vektör olarak bilinir) bakar ve aynı sıradan ikinci bir satır veya tek sütun aralığında bir değer döndürür."],
            LOWER:  ["LOWER(metin)","Bir metin dizesindeki tüm büyük harfleri küçük harfe dönüştürür."],
            MATCH:  ["MATCH(arama değeri, arama dizisi, [eşleme türü])","Bir hücre aralığındaki belirli bir öğeyi arar ve ardından o öğenin göreli konumunu aralıkta döndürür. match_type: 0, joker karakterlerini kullanma seçeneği ile tam eşleşme için; Daha az için 1 (varsayılan), lookup_array argümanındaki değerler artan sırada yerleştirilmelidir; -1'den büyük, lookup_array argümanındaki değerler azalan sırada yerleştirilmelidir."],
            MAX:  ["MAX(sayı1, [sayı2], ...)","Bir değerler kümesindeki en büyük değeri döndürür."],
            MEDIAN:  ["MEDIAN(sayı1, [sayı2], ...)","Verilen sayıların medyanını döndürür. Ortanca sayıların ortasındaki sayıdır."],
            MID:  ["MID(metin, başlangıç sayısı, karakter sayısı)","Belirttiğiniz konumda başlayarak belirttiğiniz karakter sayısına bağlı olarak bir metin dizesinden belirli sayıda karakter döndürür."],
            MIN:  ["MIN(sayı1, [sayı2], ...)","Bir değerler kümesindeki en küçük sayıyı döndürür."],
            MODE:  ["MODE(sayı1,[sayı2],...)","Bir dizi veya veri aralığındaki en sık ortaya çıkan veya tekrarlayan değeri döndürür."],
            MONTH:  ["MONTH(seri no)","Bir seri numarası ile temsil edilen tarihin ayını döndürür. Ay 1 (Ocak) ila 12 (Aralık) arasında değişen bir tamsayı olarak verilir.."],
            OR:  ["OR(Mantıksal1, [Mantıksal2], ...)","Argümanlarından herhangi biri Doğru olarak değerlendirilirse Doğru değerini döndürür ve tüm argümanları Yanlış olarak değerlendirirse Yanlış değerini döndürür."],
            PI:  ["PI()","Matematiksel sabit pi sayısını 3.14159265358979 döndürür."],
            POWER:  ["POWER(sayı, güç)","Bir güce yükseltilmiş bir sayının sonucunu döndürür."],
            PRODUCT:  ["PRODUCT(sayı1, [sayı2], ...)","argümanlar olarak verilen tüm sayıları çarpar ve ürünü döndürür. PRODUCT (A1: A3, C1: C3) = A1 * A2 * A3 * C1 * C2 * C3'e eşdeğerdir."],
            PROPER:  ["PROPER(metin)","Bir metin değerinin her kelimesindeki ilk harfi büyük harfle yazar."],
            RADIANS:  ["RADIANS(açı)","Derece radyan dönüştürür."],
            RAND:  ["RAND()","Eşit dağılımlı bir rasgele gerçek sayıyı 0'a eşit veya daha büyük ve 1'den küçük bir eşitlikte döndürür."],
            RANK:  ["RANK(sayı, ref [no])","Sayıların listesindeki bir sayının sırasını döndürür."],
            RATE:  ["",""],
            REPLACE: ["REPLACE(Eski Metin, Başlangıç No, Karakter Sayısı, Yeni Metin)","Metin dizesinin bir bölümünü, belirttiğiniz karakter sayısına bağlı olarak farklı bir metin dizesiyle değiştirir."],
            REPT: ["REPT(metin, Kaç Kez)","Metni belirli bir sayıda tekrarlar."],
            RIGHT:  ["RIGHT(metin,[karakter sayısı])","belirttiğiniz karakter sayısına bağlı olarak bir metin dizesindeki son karakteri veya karakterleri döndürür."],
            ROUND:  ["ROUND(sayı, hane)","bir sayının belli bir hane sonrasını yuvarlar.."],
            ROUNDDOWN:  ["ROUNDDOWN(sayı, hane)","verilen hane sayısı kadar aşağıya yuvarlar."],
            ROUNDUP:  ["ROUNDUP(sayı, hane)","verilen hane sayısı kadar yukarıya yuvarlar."],
            ROW:  ["ROW()","işlevinin göründüğü hücrenin referansını değerini döndürür."],
            ROWS:  ["ROWS(dizi)","Bir referans veya dizi içindeki satır sayısını döndürür."],
            SEARCH:  ["SEARCH(Aranan Metin,Metin,[Başlangıç Sütünu])","Bir metin dizesini ikinci bir metin dizesi içinde bulur ve ilk metin dizesinin başlangıç konumunun numarasını döndürür."],
            SIN:  ["SIN(sayı)","Verilen açının sinüsünü döndürür (radyan cinsinden)."],
            SMALL:  ["SMALL(dizi, k)","Veri kümesindeki k-th en küçük değerini döndürür."],
            SQRT:  ["SQRT(sayı)","Pozitif karekök verir."],
            STDEV:  ["STDEV(sayı1,[sayı2],...)","Bir numuneye göre standart sapmayı tahmin eder. Standart sapma, değerlerin ortalama değerden ne kadar yaygın olduğunu gösteren bir ölçektir (ortalama)."],
            STDEVP:  ["STDEVP(sayı1,[sayı2],...)","Bağımsız değişken olarak verilen popülasyonun tamamına dayalı standart sapmayı hesaplar."],
            SUBSTITUTE: ["SUBSTITUTE(metin, eski_, new_text, [instance_num])","Substitutes new_text for old_text in a text string."],
            SUM:  ["SUM(sayı1,[sayı2],...)","Argümanlarını ekler. Tek tek değerler, hücre referansları veya aralıkları veya her üçünün bir karışımını ekleyebilirsiniz."],        //different from actual specs,
            SUMIF:  ["SUMIF(aralık, kriter, [toplama aralığı])","Değerleri, belirttiğiniz ölçütleri karşılayan bir aralıkta ekler."],
            SUMIFS:  ["SUMIFS(Toplama Aralığı, Kriter Aralığı-1, Kriter-1, [Kriter Aralığı-2, Kriter-2], ...)","çoklu ölçütlere uyan tüm argümanlarını ekler."],
            SUMPRODUCT:  ["SUMPRODUCT(Dize1, [Dize2], [Dize3], ...)","Verilen dizilerde karşılık gelen bileşenleri çarpar ve bu ürünlerin toplamını döndürür"],
            TAN: ["TAN(sayı)","Verilen açının tanjantını (radyan cinsinden) döndürür."],
            TEXT: ["TEXT(Biçimlendirmek istediğiniz değer,\"Uygulamak istediğiniz format kodu\")","TEXT işlevi, biçim kodları ile biçimlendirmeyi uygulayarak bir sayının görüntülenme şeklini değiştirmenizi sağlar."],
            TIME:  ["TIME(saat, dakika, saniye)","Belirli bir süre için ondalık sayıyı döndürür."],
            TIMEVALUE:  ["TIMEVALUE(zaman metni)", "Bir metin dizesi tarafından temsil edilen sürenin ondalık sayısını döndürür. Ondalık sayı, 0 (sıfır) ile 0,99988426 arasında değişen bir değerdir ve 0:00:00 (12:00:00) ile 23:59:59 (11:59:59) saatleri arasındaki süreyi temsil eder."],
            TODAY:  ["TODAY()","Geçerli tarihin seri numarasını döndürür."],
            TRIM:  ["TRIM(metin)","Sözcükler arasındaki tek boşluklar hariç tüm boşlukları metinten kaldırır."],
            TRUNC:  ["TRUNC(sayı, [hane])","Sayının kesirli bölümünü kaldırarak bir sayıyı bir tam sayıya keser."],
            UPPER:  ["UPPER(metin)","Metni büyük harfe dönüştürür."],
            VALUE:  ["VALUE(metin)","Bir sayıyı bir sayıya temsil eden bir metin dizesini dönüştürür."],
            VAR:  ["VAR(sayı1,[sayı2],...)","Bir örneğe dayalı varyansı tahmin eder."],
            VARP:  ["VARP(sayı1,[sayı2],...)","Tüm popülasyona göre varyansı hesaplar."],
            VLOOKUP:  ["VLOOKUP (Arama Değeri, Tablo Dizisi, Kolon Sıra Numarası, [Aralık Araması])","Tablodaki veya bir aralıktaki bir değere sırayla bakın. Örneğin, parça numarasına göre bir otomotiv parçasının fiyatını arayın."],
            YEAR:  ["YEAR(Sıra Numarası)","Bir tarihe karşılık gelen yılı döndürür. Yıl 1900-9999 aralığında bir tam sayı olarak döndürülür."]
        }
    },
    pager = pq.pqPager.regional[local] = {
        strDisplay: "{2} Kayıttan {0} ile {1} arası görüntüleniyor.",
        strFirstPage:  "İlk Sayfa",
        strLastPage:  "Son Sayfa",
        strNextPage:  "Sonraki Sayfa",
        strPage: "{1} Sayfadan {0} gösteriliyor",
        strPrevPage:  "Önceki Sayfa",
        strRefresh: "Yenile",
        strRpp: "Gösterilecek Kayıt: {0}"
    };

    $.extend( pq.pqGrid.defaults, grid );
    $.extend( pq.pqPager.defaults, pager );

})(jQuery)
