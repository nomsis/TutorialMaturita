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
    
    ```
    {
       "rules": {
          ".read": "now < 1644879600000",  // 2022-2-15
          ".write": "now < 1644879600000",  // 2022-2-15
       }
    }
     ```
    změníte na:     
    ```
    {
    "rules": {
    ".read": true,
    ".write": true
     }
   }
   
a kliknete na "Publish".
Tutoriál na propojení vašeho projektu s FIREBASE:
https://www.youtube.com/watch?v=nep85PD8U7M

Při vytváření projektu v android studiu si vytvoříme projekt s "Empty Activity"

6.  Nastavení manifestu

    Každou třídu, která bude sloužit jak aktivita pro zobrazení nějaké stránky, musíme definovat v manifestu 
    ```("manifests->AndroidManifest.xml").
    Definujeme to pomocí 
    ```<activity android:name=".AddRecept"/> ".AddRecept - je název třídy." 

7.  Extends AppCompatActivity
  
    Nastavíme třídu aby dědila od třídy "AppCompatActivity", je to základní třída pro aktivity, která se automaticky nachází ve vytvořeném projektu, dědičnost             nastavíme pomocí "extends". "public class MainActivity extends AppCompatActivity"
    a také ji musíte naimportovat, pokud ji nenaimportujete bude vám to házet chybu. Import vypadá takto:
    "import androidx.appcompat.app.AppCompatActivity;"
 
8. Ve složce "res" vytvoříme složku ("Android Resource Directory") "menu", a vytvoříme "New->Menu Resource File" a  pojmenujeme ho "main_menu"
   Pomocí zobrazení "Design" přidáme "Menu Item" a přejdeme do kódu. Hodnota atributu "android:title" slouží jako název položky v menu. V našem případě nastavíme na      "home", nastavíme položce atribut "id" android:id="@+id/home" a dále přidáme atribut, aby byla položka vždy zobrazena app:showAsAction="always". zobrazíme menu, kód voláme ve třídě, kde potřebujeme menu zobrazit, v našem případě to bude například v `"AddRecept"` 
   
    Zobrazeni menu pomocí kódu: 
    
    ```
    public boolean onCreateOptionsMenu(Menu menu){
    
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu); // main_menu" je název našeho menu 
    return true;} 
    zobrazíme menu, kód voláme ve třídě, kde potřebujeme menu zobrazit, v našem případě to bude například v `"AddRecept"` 
    
    Nastavení položek v menu:
   
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    
    switch (item.getItemId()) {
    case R.id.home: //"home" je hodnota atributu "id", které jsme si nastavili pro naše menu 
   Intent ht1 = new Intent(Activity.this, MainActivity.class); 
    startActivity(ht1);
    return true; 
    default:return super.onOptionsItemSelected(item); } }
    
    nastavíme položkám v menu, "co mají dělat". Položce "home", která má "id=home" nastavíme, že má přepnout na třídu `"MainActivity".`


9. Třída Recept
    
    Třídu recept potřebujeme k vytvoření proměnných String, konstruktoru a metody pro získávání dat z databáze. Bude to vypadat takhle:   
    
    ```
    public class Recept { 
    
    String Nazev; // Vytvoříme si proměnu String s název "Nazev" což je vlastně textová proměná 
    String Suroviny; // Vytvoříme si proměnu String s název "Suroviny" což je vlastně textová proměná 
    String Postup; // Vytvoříme si proměnu String s název "Postup" což je vlastně textová proměná 
    
    public Recept() // vytvoříme si konstruktor Recept 
    { 
    }    
    
    public String getNazev() { //pro získání hodnoty Nazev
        return Nazev; // vrací hodnotu Nazvu
    }  
   public void setNazev(String nazev) { // pro nastavení hodnoty Nazev
        Nazev = nazev; // z parametru, který nastavíme to uloží do proměnné.
    }   
   public String getSuroviny() { // pro získání hodnoty Suroviny
        return Suroviny; // vrací hodnotu Suroviny
    }

    public void setSuroviny(String suroviny) { // pro nastavení hodnoty Suroviny
        Suroviny = suroviny; // z parametru, který nastavíme to uloží do proměnné.
    }

    public String getPostup() { // pro získání hodnoty Postup
        return Postup; // vrací hodnotu Postup
    }

    public void setPostup(String postup) { // pro nastavení hodnoty Postup
        Postup = postup; // z parametru, který nastavíme to uloží do proměnné.
    }
   }
 
10. Ukládání dat do vaší databáze
      
      Již máme vytvořenou a propojenou databázi s vaším projektem v android studiu, tak budete potřebovat jak uložit data do dané databáze. Pokud máte již vytvořený nějaký layout a třídu tak budete potřebovat následující kódy do vaší třídy abyste nahráli data do vaší databáze. Vytvoříme si třídu, kterou budeme potřebovat na vkládání dat do databáze a v ní si uděláme funkci, která to zapisování bude dělat. Já mám vytvořenou třídu `"AddRecept"` a v ní mám funkci, která mi zapisuje data do databáze.
      
Celá třída vypadá nějak takto a v ní si úkažeme co tam všechno mám:  
    
 ```     
 public class AddRecept extends AppCompatActivity {
        
    EditText nazev, suroviny, postup; // Zde si definujete vaše layout stránky
    Recept recept; // Zde si definujete vaši třídu Recept 
    DatabaseReference reff; // umožnuje vám konkrétní umístění ve vaší databázi a lze ji použít pro čtení nebo zápis dat do tohoto umístění databáze   
    
    protected void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);  
    setContentView(R.layout.add_recept); // Nastavujete jaký vzhled stránky se má načíst.
    nazev = findViewById(R.id.editReceptName); // do nazev ukládáte položku z vašeho layoutu pomocí id.
    suroviny = findViewById(R.id.editReceptResources); // do nazev ukládáte položku z vašeho layoutu\ pomocí id.
    postup = findViewById(R.id.editReceptProcess); // do nazev ukládáte položku z vašeho layoutu pomocí id.
    }
    
    Tohle je funkce, které mi zapisuje data do databáze:
      
    public void zapisData(View view) {
           
        nazev = (EditText) findViewById(R.id.editReceptName); // Zde si určujete, že výsledek bude v EditTextu a hledatého pomocí layout id
        suroviny = (EditText) findViewById(R.id.editReceptResources); // Zde si určujete, že výsledek bude v EditTextu a hledatého pomocí layout id
        postup = (EditText) findViewById(R.id.editReceptProcess); // Zde si určujete, že výsledek bude v EditTextu a hledatého pomocí layout id.
        recept = new Recept(); // Zde si vytvoříme novou instanci třídy Recept.
        reff = FirebaseDatabase.getInstance().getReference("Recept"); // Zde si definujete v jaké struktuře chcete mít data uložena. "Recept"
        recept.setNazev(nazev.getText().toString().trim()); //uloží nám do proměnné Nazev ve třídě recept obsah v závorce.(v našem případě to co napíšeme do políčka)
        recept.setSuroviny(suroviny.getText().toString().trim()); //uloží nám do proměnné Suroviny ve třídě recept obsah v závorce.(v našem případě to co napíšeme do políčka)
        recept.setPostup(postup.getText().toString().trim()); // uloží nám do proměnné Postup ve třídě recept obsah v závorce.(v našem případě to co napíšeme do políčka)
        reff.child(nazev.getText().toString().trim()).setValue(recept); // vytvoří podsložku ve složce recept a nastaví hodnoty z předchozích řádků kódu.
        Toast.makeText(AddRecept.this, "Zapisuji data.", Toast.LENGTH_SHORT).show(); // Vypíše vám hlášku, že "Zapisuji data"
        Intent ht1 = new Intent(AddRecept.this, MainActivity.class); // popisujeme operaci, která se má provést
        startActivity(ht1); // startuje tu danou operaci
        }
        }
``` 
11. Výpis dat z databáze
    
    Výpis se bude skládat ze dvou částí první část bude ve třídě MainActivity a druhá část se bude skládat ze třídy ReceptAdapter. Důležitou části je mít také vytvořený layout s recyclerView, který potřebujete na zobrazování dat
    
    Třída MainActivity se bude skládat z:

   ``` 
   EditText nazev, suroviny, postup; // Zde si definujete vaše layout stránky
   RecyclerView recyclerView; // Zde si definujete pojmenování vašeho recyclerView
   DatabaseReference reff; //DatabaseReference vám umožnuje konkrétní umístění ve vašídatabázi a lze ji použít pro čtení nebo zápis dat do tohoto umístění databáze.
   ReceptAdapter adapter; // Zde si definujete pojmenování vašeho adapteru
            
    @Override // upozorní, že se přepisuje metoda mateřské třídy   
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recepts); // nastavíme si layout stránku, kterou chcete zobrazit
        reff = FirebaseDatabase.getInstance().getReference("Recept"); // Zde si definujete v jaké struktuře chcete mít data uložena. "Recept"
        recyclerView = findViewById(R.id.recyclerRecepts); // uložíte si recyclerView, který budete používat
        
        //Slouží k zobrazení vašich přidaných dat do databáze a nastavujete ho na lineární zobrazení
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
       
       //Když jsou data přidána, odebrána nebo změněna, tyto aktualizace se automaticky aplikují na vaše uživatelské rozhraní v reálném čase.
        
        FirebaseRecyclerOptions<Recept> options1
                = new FirebaseRecyclerOptions.Builder<Recept>()
                .setQuery(reff, Recept.class)
                .build();
        adapter = new ReceptAdapter(options1); // Vytvoříme instanci třídy ReceptAdapter
        recyclerView.setAdapter(adapter);
        nazev =  findViewById(R.id.editReceptName); // do nazev ukládáte položku z vašeho layoutu pomocí id.
        suroviny = findViewById(R.id.editReceptResources); // do suroviny ukládáte položku z vašeho layoutu pomocí id.
        postup =  findViewById(R.id.editReceptProcess); // do postup ukládáte položku z vašeho layoutu pomocí id.
        }
    
    //Zde si spouštítě váš adaptér 
     @Override    
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
   
   //Zde stopujete váš adaptér
    @Override    
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
     
   

Druhá část vypísu dat, která je ve třídě ReceptAdapter:

   ``` 
   public class ReceptAdapter extends FirebaseRecyclerAdapter<Recept, ReceptAdapter.receptViewholder> {
   
   public ReceptAdapter(@NonNull FirebaseRecyclerOptions<Recept> options) { // Konstruktor vašeho Adapteru   
    
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



Activity.xml 
<?xml version="1.0" encoding="utf-8"?>
<ScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true"
    tools:context=".Activity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        >


    <TextView
        android:id="@+id/textNazev"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Recept"
        android:textSize="40dp"
        android:gravity="center"
        android:layout_marginTop="50dp"
        android:textColor="@color/black"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.106" />

    <TextView
        android:id="@+id/textSuroviny"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="TextView"
        android:layout_marginTop="40dp"
        android:gravity="center"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.229"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.328" />

    <TextView
        android:id="@+id/textPostup"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="TextView"
        android:layout_marginTop="40dp"
        android:gravity="center"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.229"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.515" />

        <View
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"
            />

    <Button
        android:id="@+id/editBtn"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="rozklikEdit"
        android:text="Úprava receptu"
        android:layout_margin="20dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.866" />

    </LinearLayout>
</ScrollView>


add_recept.xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".AddRecept">


    <TextView
        android:id="@+id/textCategoryName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Napište název jídla"
        android:textColor="@color/black"
        android:textSize="18dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.056" />

    <EditText
        android:id="@+id/editReceptName"
        android:layout_width="346dp"
        android:layout_height="43dp"
        android:gravity="center"
        android:hint=""
        android:text=""
        android:textColor="@color/black"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.492"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textCategoryName"
        app:layout_constraintVertical_bias="0.022" />

    <EditText
        android:id="@+id/editReceptResources"
        android:layout_width="345dp"
        android:layout_height="121dp"
        android:background="@drawable/border"
        android:gravity="top"
        android:hint=""
        android:text=""
        android:textColor="@color/black"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textReceptResources"
        app:layout_constraintVertical_bias="0.02" />

    <EditText
        android:id="@+id/editReceptProcess"
        android:layout_width="370dp"
        android:layout_height="254dp"
        android:background="@drawable/border"
        android:gravity="top"
        android:hint=""
        android:text=""
        android:textColor="@color/black"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.39"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textReceptProcess"
        app:layout_constraintVertical_bias="0.081" />

    <Button
        android:id="@+id/btnConfirmAddRecept"
        android:layout_width="143dp"
        android:layout_height="57dp"
        android:layout_marginBottom="8dp"
        android:onClick="zapisData"
        android:text="Přidat"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.511"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.987" />

    <TextView
        android:id="@+id/textReceptResources"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Vypište potřebné suroviny"
        android:textColor="@color/black"
        android:textSize="18dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.222" />

    <TextView
        android:id="@+id/textReceptProcess"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Napište postup receptu"
        android:textColor="@color/black"
        android:textSize="18dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.483" />

</androidx.constraintlayout.widget.ConstraintLayout>

edit_activity.xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".EditActivity">


    <TextView
        android:id="@+id/textNazev"
        android:layout_width="140dp"
        android:layout_height="44dp"
        android:text="Název"
        android:textColor="@color/black"
        android:textSize="20dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.154"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.068" />

    <EditText
        android:id="@+id/editNazev"
        android:layout_width="344dp"
        android:layout_height="39dp"
        android:text="a"
        android:textColor="@color/black"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.626"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textNazev"
        app:layout_constraintVertical_bias="0.028" />

    <TextView
        android:id="@+id/textSuroviny"
        android:layout_width="277dp"
        android:layout_height="33dp"
        android:text="Suroviny"
        android:textColor="@color/black"
        android:textSize="20dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.313"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/editNazev"
        app:layout_constraintVertical_bias="0.056" />

    <EditText
        android:id="@+id/editSuroviny"
        android:layout_width="346dp"
        android:layout_height="42dp"
        android:text="a"
        android:textColor="@color/black"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.646"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textSuroviny"
        app:layout_constraintVertical_bias="0.071" />

    <EditText
        android:id="@+id/editPostup"
        android:layout_width="262dp"
        android:layout_height="82dp"
        android:layout_marginBottom="176dp"
        android:text="a"
        android:textColor="@color/black"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.308"

        app:layout_constraintStart_toStartOf="parent" />

    <TextView
        android:id="@+id/textPostup"
        android:layout_width="291dp"
        android:layout_height="44dp"
        android:layout_marginBottom="300dp"
        android:text="Postup"
        android:textColor="@color/black"
        android:textSize="20dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.383"

        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/btnUlozit"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Uložit"
        android:onClick="editDat"
        app:layout_constraintBottom_toBottomOf="parent"
        android:textColor="@color/black"
        app:backgroundTint="#43CAF3"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.902"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.948" />

    <Button
        android:id="@+id/btnOdstranit"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="148dp"
        android:onClick="odstranitData"
        android:text="Odstranit"
        android:textColor="@color/black"
        app:backgroundTint="#43CAF3"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/btnUlozit"
        app:layout_constraintHorizontal_bias="1.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.948" />
</androidx.constraintlayout.widget.ConstraintLayout>



recepts.xml

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerRecepts"
        android:layout_width="404dp"
        android:layout_height="572dp"
        android:layout_marginTop="16dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.428"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.333" />

    <TextView
        android:id="@+id/textRecepts"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Recepty"
        android:textColor="@color/black"
        android:textSize="35dp"
        app:layout_constraintBottom_toTopOf="@+id/recyclerRecepts"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="1.0" />

    <Button
        android:id="@+id/btnAddRecept"
        android:layout_width="180dp"
        android:layout_height="63dp"
        android:onClick="AddRecept"
        android:text="Přidat recept"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/recyclerRecepts"
        app:layout_constraintVertical_bias="0.232" />
</androidx.constraintlayout.widget.ConstraintLayout>


single_view_layout.xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginHorizontal="40dp"
    android:layout_marginTop="10dp"
    android:layout_marginBottom="20dp"
    android:scrollbars="vertical"
    app:cardCornerRadius="20dp"
    tools:context=".MainActivity">


    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="389dp"
        android:layout_height="60dp">

        <TextView
            android:id="@+id/Nazev"
            android:layout_width="276dp"
            android:layout_height="34dp"
            android:gravity="center"
            android:text="TextView"
            android:textColor="@color/black"
            android:textSize="20dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.141"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.47" />

        <Button
            android:id="@+id/btnRozklik"
            android:layout_width="410dp"
            android:layout_height="120dp"
            android:layout_marginTop="16dp"
            app:backgroundTint="#3E00FFF7"
            app:layout_constraintBottom_toBottomOf="@+id/Nazev"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="1.0"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.511" />

    </androidx.constraintlayout.widget.ConstraintLayout>
</androidx.cardview.widget.CardView>





    
    
   
    
