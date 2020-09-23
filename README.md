
**
## Kütüphane Uygulaması

**
**  KURULUM
Uygulamayı kurmak için yapılması gerekenler şöyle;

 - İlk önce bilgisayrınızda javayı çalıştıracak editörün bulunması gerekiyor.
 - Apache Tomcat 7,8,9 sürümlerinden biri indirilip kurulmalıdır.Bu proje Apache Tomcat 8 ile geliştirildi.
 - Mysql en son sürümünü indiriniz.Mysql Workbench olması da sorun
   yaratmayacaktır. Bu kısımda en önemli nokta Mysql'in kurulmasıdır.
 - Baştaki 3 adımı uyguladıktan sonra sistemsel olarak sorun    olmayacaktır.Projeyi çalıştırmak için ; "Eclipse IDE->File->Import-> Git -> Project from Git -> Clone URL ->" sekmelerine basıp proje kodunun yolunu url bölümüne yapıştırıp next butonları ile editöre dahil edebilir ve çalıştırabilirsiniz.

Projenin src/main/resources içinde "application.properties" adında bir adet dosya bulunmaktadır. Bu dosya sebebiyle uygulama çalışmayacaktır.Bu yüzden şu kod segmentlerini değiştirelim;

     spring.datasource.url=jdbc:mysql://localhost:3306/library_application

    

Kurduğunuz Mysql'de "library_application" isminde bir bağlantı oluşturmanız gerekiyor.Tabiki bu adı değiştirip kendi yarattığınız bir bağlantı adresini verebilirsiniz. Ayrıca sizin Mysql kurulumu sırasında belirlediğiniz kullanıcı adı ve şifre de farklı olacaktır.Aşağıdaki kod satırlarına kendi kullanıcı adı ve şifrelerinizi yazınız;

  

    spring.datasource.username=root
    spring.datasource.password=123456
 

Bu anlatıklarım başarıyla yapılmış ise uygulama düzgün çalışacaktır.


**
## 
**
**  KULLANIM
Projenin kodlanması ve geliştirilmesi kısmında  Spring,JPA,Thymeleaf,MAVEN ve Bootstrap kullanılmıştır.
Projeyi çalıştırınca  karşımıza direkt kullanıcı adı ve şifreyle giriş yapılan sayfa açılıyor. Bu bölümü kodlarken bootstraptan kütüphanesinden ve thymelaften yararlandım. 
Henüz kayıtlı değilseniz sisteme kayıt olma bölümünden kayıt olabilir daha sonra sisteme giriş yapabilirsiniz.Kullanıcı adına sahip değilseniz ve sisteme giriş yapmadıysanız herhangi bir sayfaya girişiniz gerçekleştirilmiyor. Sizden ilk önce kullanıcı girişi yapmanız isteniyor.
Bunun için yazmış olduğum kod bölümünü alt kısımda görebilirsiniz.

        .antMatchers("/*").authenticated()
		.antMatchers("/home/**").hasAnyRole("ADMIN","USER")
		.antMatchers("/book/**").hasRole("ADMIN")
		.antMatchers("/author/**").hasRole("ADMIN")
		.antMatchers("/publisher/**").hasRole("ADMIN")

Kayıt yapıldığında sıradan bir kullanıcı olmuş oluyorsunuz. Spring security sayesinde sistemdeki kullanıcının rolüne göre kısıtlama işlemlerini gerçekleştirdim. 

    sec:authorize="hasRole('ROLE_ADMIN')"  metodu ile sadece admin rolüne sahip olan kişilerin görebileceği bir kontrol mekanizması ekledim.

Giriş yaptığınızda sistemde kayıtlı olan kitapları, yazarları ve yayınevlerinin isimlerini görebilirsiniz. Bu verileri sayfada listelerken thymeleafden faydalandım. Thymeleafin each metodu sayesinde listemdeki elemaların herbirini tablomun elemanı olarak listeledim. Sizler bu verilere herhangi bir düzenleme gerçekleştiremezsiniz. Aynı zamanda yeni bir kayıt oluşturma yetkiniz de bulunmuyor. Fakat veritabanındaki user tablosuna yeni bir kullanıcı eklerseniz ve rolüne ROLE_ADMIN olarak belirtirseniz artık kullanıcı girişi yaptığınızda spring sizin bir admin olduğunuzu anlar ve sizlerin yetkinize göre izinleri veriyor.

  Anasayfada yazar ekle, yayınevi ekle ve kitap ekle seçenekleri ile yazar ve yayınevi ekledikten sonra kitap ekleme işlemini de gerçekleştirebilirsiniz. Ekleme yaparken kitap açıklama, yazar açıklama ve yayınevi açıklama kısımları dışındaki bölümleri required özelliği ile mecbur olarak girilmesi gerekecek. Girilmediği takdirde ekrana doldurması için bir mesaj gösterilecek. Alt kısımda gösterdiğim gibi.

-
    <input type="text" class="form-control" id="seriesName" name="seriesName" th:field="*{seriesName}"  placeholder="Enter Series Name" required>

Ana ekranda kitap ara bölümlerine tıklayarak kitabın ismine göre, yazarına göre, isbn numarası ve seri adına göre arama işlemlerini gerçekleştirebilirsiniz. Bu bölümde jquery kütüphanesinden faydalanarak ekranda gereksiz yer kaplamaması için açılan bir form yazdım. Form bölümünde hangi kısımda kelime yazıyorsa onun için arama yapıyoruz.Bu sorgular için JpaRepositoryden faydalanarak basit bir şekilde arama işlemlerini gerçekleştirdim. 
Alt kısımda gösterdiğim şekilde.

    public interface BookRepository extends JpaRepository<Book, Integer>{
	Book findById(int id);
	List<Book> findByBookName(String bookName);
	List<Book> findBySeriesName(String seriesName);
	List<Book> findByIsbn(String isbn); }
Yazar adına göre arama ve silme işlemi için tek satırlık aynı şekilde basit bir kod yazdım.

    @Query("select b from Book b where b.author.name=?1")
	List<Book> findByAuthorName(String name);
	
	@Modifying
	@Transactional
	@Query("delete from Book b where b.id=?1")
	int deleteBook(int id);


Son olarak ana ekrandaki kitapların,yazarların ve yayınevlerinin yanında bulunan sil butonu ile kayıtlı olan veriyi silebilir veya düzenle butonu ile verinin özelliklerini idsinden faydalanarak düzenleme sayfasına aktararak düzenleme işlemlerini gerçekleştirebilirsiniz. Ve sağ üst bölümde logout butonuna tıklayarak sistemden çıkış yapabilirsiniz ve sizi direkt login sayfasına yönlendirir. Bunu içinde springsecuriy bölümünde yazmış olduğum kod parçacığını bir alta bırakıyorum.



	.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");



İyi günler.


