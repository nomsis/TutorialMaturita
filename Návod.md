1. Záčátek

    Začátek pro všechny čtenáře je to tutoriál na mobilní aplikace s použití databáze FIREBASE. Jedná se o návod, který vás provede celou problematikou a díky němu        byste měli být schopni udělat vlastní aplikaci s databází. Pro ukázku jsem vytvořil jednoduchou mobilní aplikaci kuchařka, do které můžete přidávat recepty, dále je    můžete upravit a nakonec i smazat. 

2. Výběr programu na aplikace
    
     Na tvorbu aplikace jsem si zvolil aplikaci Android Studio, je to jeden z nejznámějších programů na tvorbu aplikací. Můžete v něm psát v programovacím jazyku       Java a nebo Kotlin. Osobně jsem si vybral programovací jazyk Java, jelikož jsem již měl nějaké základy ve škole a je zde hodně věcí podobných. 
    Android studio si můžete stáhnout zde: https://developer.android.com/studio 

3. Výběr databáze
    
    Jako databázi jsem si zvolil FIREBASE, jelikož je velice populární a dobře se mi s ní pracovalo již v minulosti. 
    Budete si muset vytvořit účet zde, a když kliknete na tlačítko "Get Started" tak si vytvoříte účet na FIREBASE:
    https://firebase.google.com/?gclid=Cj0KCQiAoY-PBhCNARIsABcz7707gg2dCuHahe9rnnYzMkrsscrEN24fhgT1fr7SYOeCqQLy3-J13u0aAtLAEALw_wcB&gclsrc=aw.ds 
    
4. Vytvoření projektu ve FIREBASE
 
    Po přihlášení do webové stránky Firebase na odkazu výše, tak kliknete na tlačítko "+ Add project". Poté zadáte váš název vaší databáze. Můžete zvolit jakýkoliv název, nejlépe aby se vám dobře pamatoval. Poté si vyberete, že chcete použít účet, který jste si vytvořili a kliknete na tlačítko "create project". Jakmile se vám projekt vytvoří kliknete na tlačítko "continue". 

5. Propojení android studia s vaším vytvořeným projektem ve FIREBASE

    V android studiu na kartě si zvolíte "Tools->FIREBASE". Zde se vám objeví okno, kde si zvolíte Realtime database a zvolíte "Get Started with Realtime Database". Poté kliknete na Connect to Firebase zeptá se vás to na hlášku, kterou potvrdíte tlačítkem "build". Po rozkliknutí "connect to Firebase" vás to odkáže na web Firebase, kde si budete muset vytvořit projekt, a nebo si vyberete projekt, který jste si již vytvořili a propojíte ho.  Poté v android studiu budete mít "Add the Realtime Database SDK to your app" a zvolíte "accept changes" budete muset kliknout 2x. Pokud se vám neukáže zelená šipka, budete si to muset ručně nahrát. Nahrajeme tak, že si zkopírujeme danný požadavek a vložíme jej do Grandle Script -> build.grandle(Module a název vašeho projektu) a zde si ho naimplemenujete. Poté půjdete na WEB vašeho projektu a kliknete na "Realtime database",  a kliknete na Create Database zde si vybere United States, dá se říci, že je to jedno co si vyberete jelikož je to místo, kde se budou ukládat vaše data. A jako druhé si zvolíte "Start in test mode" a kliknete na tlačítko Enable. Budete mít databázi vytvořenou a musíte změnit jednu věc a tu najdete v položce  Rules a zde budete mít vypsané toto, a budete to muset změnit:
    
    `{`\
    `"rules": {` \
    `".read": "now < 1644879600000",  // 2022-2-15`\
   ` ".write": "now < 1644879600000",  // 2022-2-15`\
    `}`\
   ` }`\
    změníte na: 
    
    `{`\
    `"rules": {`\
    `".read": true,`\
    `".write": true`\
     `}`\
   ` }`
   
a kliknete na "Publish".
Tutoriál na propojení vašeho projektu s FIREBASE:
https://www.youtube.com/watch?v=nep85PD8U7M

6.  Nastavení manifestu

    Každou třídu, která bude sloužit jak aktivita pro zobrazení nějaké stránky, musíme definovat v manifestu 
    `("manifests->AndroidManifest.xml").` 
    Definujeme to pomocí `<activity android:name=".AddRecept"/>` `.AddRecept - je název třídy.` 

7.  Extends AppCompatActivity
  
    Nastavíme třídu aby dědila od třídy "AppCompatActivity", je to základní třída pro aktivity, která se automaticky nachází ve vytvořeném projektu, dědičnost             nastavíme pomocí "extends". "public class MainActivity extends AppCompatActivity"
    a také ji musíte naimportovat, pokud ji nenaimportujete bude vám to házet chybu. Import vypadá takto:
    "import androidx.appcompat.app.AppCompatActivity;"
 
8. Ve složce "res" vytvoříme složku ("Android Resource Directory") "menu", a vytvoříme "New->Menu Resource File" a  pojmenujeme ho "main_menu"
   Pomocí zobrazení "Design" přidáme "Menu Item" a přejdeme do kódu. Hodnota atributu "android:title" slouží jako název položky v menu. V našem případě nastavíme na      "home", nastavíme položce atribut "id" android:id="@+id/home" a dále přidáme atribut, aby byla položka vždy zobrazena app:showAsAction="always".
   
    Zobrazeni menu pomocí kódu: 
    
    `public boolean onCreateOptionsMenu(Menu menu){` \
    
    `MenuInflater inflater = getMenuInflater();` \
    `inflater.inflate(R.menu.main_menu, menu);` // main_menu" je název našeho menu \
    `return true;}` \
    zobrazíme menu, kód voláme ve třídě, kde potřebujeme menu zobrazit, v našem případě to bude například v `"AddRecept"` 
    
    Nastavení položek v menu:
    
    `public boolean onOptionsItemSelected(@NonNull MenuItem item) {` \
    
    `switch (item.getItemId()) {`\
    `case R.id.home:` //"home" je hodnota atributu "id", které jsme si nastavili pro naše menu \
   ` Intent ht1 = new Intent(ReceptActivity.this, MainActivity.class);` \
    `startActivity(ht1);` \
    `return true;` \
    `default:return super.onOptionsItemSelected(item); } }` \
    nastavíme položkám v menu, "co mají dělat". Položce "home", která má "id=home" nastavíme, že má přepnout na třídu `"MainActivity".`


9. Třída Recept
    
    Třídu recept potřebujeme k vytvoření proměnných String, konstruktoru a metody pro získávání dat z databáze. Bude to vypadat takhle:   
    
    `public class Recept {` 
    
    `String Nazev;` // Vytvoříme si proměnu String s název "Nazev" což je vlastně textová proměná \
    `String Suroviny;` // Vytvoříme si proměnu String s název "Suroviny" což je vlastně textová proměná \
    `String Postup;` // Vytvoříme si proměnu String s název "Postup" což je vlastně textová proměná 
    
    `public Recept()` // vytvoříme si konstruktor Recept \
    `{` \
   `}`
    
    
    `public String getNazev() {` //pro získání hodnoty Nazev\
        `return Nazev;` // vrací hodnotu Nazvu\
    `}`

   
   `public void setNazev(String nazev) {` // pro nastavení hodnoty Nazev\
        `Nazev = nazev;` // z parametru, který nastavíme to uloží do proměnné.\
    `}`

   
   `public String getSuroviny() {` // pro získání hodnoty Suroviny\
        `return Suroviny;` // vrací hodnotu Suroviny\
    `}`

    `public void setSuroviny(String suroviny) {` // pro nastavení hodnoty Suroviny\
        `Suroviny = suroviny;` // z parametru, který nastavíme to uloží do proměnné.\
    `}`

    `public String getPostup() {` // pro získání hodnoty Postup\
        `return Postup;` // vrací hodnotu Postup\
    `}`

    `public void setPostup(String postup) {` // pro nastavení hodnoty Postup\
        `Postup = postup;` // z parametru, který nastavíme to uloží do proměnné.\
    `}`\
   `}`
10. Ukládání dat do vaší databáze
      
      Již máme vytvořenou a propojenou databázi s vaším projektem v android studiu, tak budete potřebovat jak uložit data do dané databáze. Pokud máte již vytvořený nějaký layout a třídu tak budete potřebovat následující kódy do vaší třídy abyste nahráli data do vaší databáze. Vytvoříme si třídu, kterou budeme potřebovat na vkládání dat do databáze a v ní si uděláme funkci, která to zapisování bude dělat. Já mám vytvořenou třídu `"AddRecept"` a v ní mám funkci, která mi zapisuje data do databáze.
      
      Celá třída vypadá nějak takto a v ní si úkažeme co tam všechno mám:  
    
     `public class AddRecept extends AppCompatActivity {`\
        
        `EditText nazev, suroviny, postup;` // Zde si definujete vaše layout stránky\
        `Recept recept;` // Zde si definujete vaši třídu Recept \
        `DatabaseReference reff;` // DatabaseReference vám umožnuje konkrétní umístění ve vaší 
        databázi a lze ji použít pro čtení nebo zápis dat do tohoto umístění databáze.\
        `protected void onCreate(Bundle savedInstanceState) {`\
        `super.onCreate(savedInstanceState);`  \
        `setContentView(R.layout.add_recept);` // Nastavujete jaký vzhled stránky se má načíst. \
        `nazev = findViewById(R.id.editReceptName);` // do nazev ukládáte položku z vašeho layoutu pomocí id.\
        `suroviny = findViewById(R.id.editReceptResources);` // do nazev ukládáte položku z vašeho layoutu\ pomocí id.
        `postup = findViewById(R.id.editReceptProcess);` // do nazev ukládáte položku z vašeho layoutu pomocí id.\
    `}`\
        Tohle je funkce, které mi zapisuje data do databáze:
      
      `public void zapisData(View view) {` \
           
           `nazev = (EditText) findViewById(R.id.editReceptName);` // Zde si určujete, že výsledek bude v EditTextu a hledatého pomocí layout id\
            `suroviny = (EditText) findViewById(R.id.editReceptResources);` // Zde si určujete, že výsledek bude v EditTextu a hledatého pomocí layout id\
            `postup = (EditText) findViewById(R.id.editReceptProcess);` // Zde si určujete, že výsledek bude v EditTextu a hledatého pomocí layout id.\
        `recept = new Recept();` // Zde si vytvoříme novou instanci třídy Recept.\
        `reff = FirebaseDatabase.getInstance().getReference("Recept");` // Zde si definujete v jaké struktuře chcete mít data uložena. "Recept"\
        `recept.setNazev(nazev.getText().toString().trim());` // uloží nám do proměnné Nazev ve třídě recept obsah v závorce.(v našem případě to co napíšeme do políčka)\
        `recept.setSuroviny(suroviny.getText().toString().trim());` // uloží nám do proměnné Suroviny ve třídě recept obsah v závorce.(v našem případě to co napíšeme do políčka)\
        `recept.setPostup(postup.getText().toString().trim());` // uloží nám do proměnné Postup ve třídě recept obsah v závorce.(v našem případě to co napíšeme do políčka)\
        `reff.child(nazev.getText().toString().trim()).setValue(recept);` // vytvoří podsložku ve složce recept a nastaví hodnoty z předchozích řádků kódu.\
        `Toast.makeText(AddRecept.this, "Zapisuji data.", Toast.LENGTH_SHORT).show();` // Vypíše vám hlášku, že "Zapisuji data"\
        `Intent ht1 = new Intent(AddRecept.this, MainActivity.class);` // popisujeme operaci, která se má provést\
        `startActivity(ht1);` // startuje tu danou operaci\
        `}`\
        `}`
11. Výpis dat z databáze
    
    Výpis se bude skládat ze dvou částí první část bude ve třídě MainActivity a druhá část se bude skládat ze třídy ReceptAdapter. Důležitou části je mít také vytvořený layout s recyclerView, který potřebujete na zobrazování dat
    
    Třída MainActivity se bude skládat z:

    `EditText nazev, suroviny, postup;` // Zde si definujete vaše layout stránky\
    `RecyclerView recyclerView;` // Zde si definujete pojmenování vašeho recyclerView\
    `DatabaseReference reff;` // DatabaseReference vám umožnuje konkrétní umístění ve vaší \
    databázi a lze ji použít pro čtení nebo zápis dat do tohoto umístění databáze.\
    `ReceptAdapter adapter;` // Zde si definujete pojmenování vašeho adapteru
    
    `@Override` // upozorní, že se přepisuje metoda mateřské třídy\
    
    `protected void onCreate(Bundle savedInstanceState) {`\
        `super.onCreate(savedInstanceState);`\
        `setContentView(R.layout.recepts);` // nastavíme si layout stránku, kterou chcete zobrazit\
        `reff = FirebaseDatabase.getInstance().getReference("Recept");` // Zde si definujete v jaké struktuře chcete mít data uložena. "Recept"\
        `recyclerView = findViewById(R.id.recyclerRecepts);` // uložíte si recyclerView, který budete používat
        //Slouží k zobrazení vašich přidaných dat do databáze a nastavujete ho na lineární zobrazení\
        `recyclerView.setLayoutManager(`\
                `new LinearLayoutManager(this));`
       
       //Když jsou data přidána, odebrána nebo změněna, tyto aktualizace se automaticky aplikují na vaše                  uživatelské rozhraní v reálném čase.\
        `FirebaseRecyclerOptions<Recept> options1`\
                `= new FirebaseRecyclerOptions.Builder<Recept>()`\
                `.setQuery(reff, Recept.class)`\
                `.build();`\
        `adapter = new ReceptAdapter(options1);` // Vytvoříme instanci třídy ReceptAdapter\
        `recyclerView.setAdapter(adapter);`  \
        `nazev =  findViewById(R.id.editReceptName);` // do nazev ukládáte položku z vašeho layoutu pomocí id.\
        `suroviny = findViewById(R.id.editReceptResources);` // do suroviny ukládáte položku z vašeho layoutu pomocí id.\
        `postup =  findViewById(R.id.editReceptProcess);` // do postup ukládáte položku z vašeho layoutu pomocí id.\
        `}`
    //Zde si spouštítě váš adaptér \
     `@Override`\
    
    `protected void onStart() {`\
        `super.onStart();`\
        `adapter.startListening();`\
    `}`\
    //Zde stopujete váš adaptér\
    `@Override`\
    
    `protected void onStop() {`\
        `super.onStop();`\
        `adapter.stopListening();`\
    `}`
    
    Druhá část vypísu dat, která je ve třídě `ReceptAdapter:`\
   
   `public ReceptAdapter(@NonNull FirebaseRecyclerOptions<Recept> options) {` // Konstruktor vašeho Adapteru\
    
    `super(options);`\
    `}`\
    //nastavuje některá soukromá pole, která má RecyclerView používat.\
    `@Override`\
    `protected void onBindViewHolder(@NonNull receptViewholder holder,int position, Recept model) {`\
        `holder.nazev.setText(model.getNazev());`\
    `}`\
    //Slouží k vytvoření nového RecyclerView.ViewHoldera inicializuje některá soukromá pole, která má RecyclerView použít.\    
    
    `public receptViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {`\  
   
   `View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_layout, parent, false);`\       
       `return new ReceptAdapter.receptViewholder(view);`\
    `}`\     
    
    `class receptViewholder extends RecyclerView.ViewHolder   {`\
        `TextView nazev, suroviny, postup;`\              
       
       `public receptViewholder(@NonNull View itemView)`\
        `{`\
            `super(itemView);`\
            `nazev= itemView.findViewById(R.id.Nazev);`\
            `itemView.findViewById(R.id.btnRozklik).setOnClickListener(new View.OnClickListener() {`\
                `@Override`\
                
                `public void onClick(View view) {`\
                    `Intent ht1 = new Intent( view.getContext(), Activity.class);`\
                    `ht1.putExtra("nazev", nazev.getText());`\
                    `view.getContext().startActivity(ht1);`\
                    `FirebaseDatabase.getInstance().getReference().child("Recept");`\
                `}`\
            `});`\
        `}`\
   `}`
    
12. Třída EditActivity   
   V této třídě jsou funkce na editování a mazání dat.   
   
   `public class EditActivity extends AppCompatActivity {`
    
    `String Nazev, Suroviny, Postup, nazev2;` // vytvoříme textové proměnné\
    `EditText textNazev, textSuroviny, textPostup;`  // políčka, kde se píšou dané texty.\
    `EditText editTextZapsat;` // zapíše se daná hodnota\
    `Button btnZapsat;` // Zde si definujete id vašeho vytvořeného Buttonu\
    `DatabaseReference reff;` // DatabaseReference vám umožnuje konkrétní umístění ve vaší databázi a lze ji použít pro čtení nebo zápis dat do tohoto umístění       databáze.\
    `Recept recept;` // Zde si definujete vaši třídu Recept  

    `@Override`\
    `protected void onCreate(Bundle savedInstanceState) {`\
        `super.onCreate(savedInstanceState);`\
        `setContentView(R.layout.edit_activity);` // Nastavíte, stránku kterou chcete zobrazit\
        `Intent ht2= getIntent();` // Získáme Intent
        `Bundle b = ht2.getExtras();` // A tady uložíme do Bundle b ty extras z Intent, které jsme tam vložili\
        `nazev2 = (String) b.get("nazev2");` // do nazev2 uložíme hodnotu "nazev2", které je v balíčku b\
        `reff = FirebaseDatabase.getInstance().getReference("Recept").child(nazev2);` // uložíme si, který objekt v databázi budeme upravovat\
        `reff.addValueEventListener(new ValueEventListener() { `\
           // Tento kód zkontroluje zda existují data , pak načte data v nejhorším případě , pokud se stane něco jiného, zachytí tuto chybu a nezpůsobí pád aplikace\
            `@Override`\
            `public void onDataChange(@NonNull DataSnapshot dataSnapshot){`\
                `try {`\
                    `if(dataSnapshot.exists())`\
                    `{`\
                       `Nazev = dataSnapshot.child("nazev").getValue().toString();` // Do proměnné název, uložíme hodnotu nazvu dané položky v databázi\
                        `Suroviny= dataSnapshot.child("suroviny").getValue().toString();`\
                        `Postup  = dataSnapshot.child("postup").getValue().toString();`\
                        `textNazev = findViewById(R.id.editNazev);` \
                        `textSuroviny = findViewById(R.id.editSuroviny);`\
                        `textPostup =  findViewById(R.id.editPostup);`\
                        `textNazev.setText(Nazev);` // nastaví textu Nazev hodnotu, kterou jsme mu určili\
                        `textSuroviny.setText(Suroviny);`\
                       ` textPostup.setText(Postup);`\
                    `}`\
                `}catch (NullPointerException e){`\
                    `e.printStackTrace();`\
               `}`\
            `}`\
            `@Override`\
            `public void onCancelled(@NonNull DatabaseError databaseError){`\
            `}`\
       `});`\
    `}`
    
13. Mazání dat   
    V tomto podbodu si ukážeme jak se mažou data z vaší databáze. Já jsem si vytvořil třídu EditActivity v které mám funkce na mazání a upravování dat. 
    Funkce může vypadat nějak takto.
     
     `public void odstranitData(View view){`  
        
        `recept = new Recept();`  // Zde si vytvoříme novou instanci třídy Recept.\
        `reff = FirebaseDatabase.getInstance().getReference("Recept");` // Zde si definujete v jaké struktuře chcete mít data uložena. "Recept", a kde se daná data mají vymazat.\
        `recept.setNazev(null);` // po kliknutí na tlačítko odstranit, vymaže hodnoty promměných objektu recept\
        `recept.setSuroviny(null);` // po kliknutí na tlačítko odstranit, vymaže hodnoty promměných objektu recept\
        `recept.setPostup(null);` // po kliknutí na tlačítko odstranit, vymaže hodnoty promměných objektu recept\
        `reff.child(nazev2).setValue(recept);` // nastaví tu danou hodnotu na nulu, tím pádem se vymaže\
        `Toast.makeText(EditActivity.this, "Mažu data.", Toast.LENGTH_SHORT).show();` // Výpis po smazání dat\
        `Intent ht1 = new Intent(EditActivity.this, MainActivity.class);` // popisujeme operaci, která se má provést\
        `startActivity(ht1);` // startuje tu danou operaci\
    `}`

14. Úprava dat    
    V tomto podbodu si ukážeme jak se editují/upravují vaše data v databázi. Jak jsem již zmínil úpravu i smazání dat mám v jedné tříde EditActivity.     
   
   `public void editDat(View view) {` \
        
        `recept = new Recept();`  // Zde si vytvoříme novou instanci třídy Recept.\
        `reff = FirebaseDatabase.getInstance().getReference("Recept");` // Zde si definujete v jaké struktuře chcete mít data uložena. "Recept"\
        `recept.setNazev(textNazev.getText().toString().trim());` // uloží nám do proměnné Nazev ve třídě recept obsah v závorce.(v našem případě to co napíšeme do políčka)\
        `recept.setSuroviny(textSuroviny.getText().toString().trim());` //uloží nám do proměnné Suroviny ve třídě recept obsah v závorce.(v našem případě to co napíšeme do políčka)\
        `recept.setPostup(textPostup.getText().toString().trim());` // uloží nám do proměnné Postup ve třídě recept obsah v závorce.(v našem případě to co napíšeme do políčka)\
        //Tato část kódu dělá to, že když chceme cokoliv změnit, tak se vymaže celý recept a vloží se tam nový s novou hodnotou. \
        `reff.child(recept.getNazev()).setValue(recept);` \
        `if(!nazev2.equals(recept.getNazev()))`  \
        `reff.child(nazev2).removeValue();`  \
        `Toast.makeText(EditActivity.this, "Aktualizuji data.", Toast.LENGTH_SHORT).show();` // Výpis hlášky po úpravě dat\
        `Intent ht1 = new Intent(EditActivity.this, MainActivity.class);`  // popisujeme operaci, která se má provést\
        `startActivity(ht1);`  // startuje tu danou operaci\
    `}`







    
    
   
    
