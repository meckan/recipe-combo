package com.recipebackend;

import com.recipebackend.BO.*;
import com.recipebackend.DB.GroceryDBHandler;
import com.recipebackend.DB.RecipeDBHandler;
import com.recipebackend.DB.ShoppingListDBHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class RecipeBackendApplication {

    @Autowired
    private RecipeDBHandler recipeDBHandler;
    @Autowired
    private GroceryDBHandler groceryDBHandler;
    @Autowired
    private ShoppingListDBHandler listDBHandler;

    public static void main(String[] args) {
        SpringApplication.run(RecipeBackendApplication.class, args);
    }


    @Bean
    void populateDB() throws IOException {

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient(new Grocery("Kycklingfile","kyckling"),"gram",200));
        ingredientList.add(new Ingredient(new Grocery("Bön Pasta","pasta"),"gram",70));
        ingredientList.add(new Ingredient(new Grocery("Pesto","sås"),"msk",1));

        File file = new File("src/main/resources/IMG_1590.JPG");


        byte[] fileContentByte = Files.readAllBytes(file.toPath());

        String fileContent = "data:image/jpg;base64,";

        fileContent = fileContent + new String(Base64.getEncoder().encode(fileContentByte), StandardCharsets.UTF_8);

        Picture picture = new Picture(fileContent);

        Recipe recipe = new Recipe("Kyckling pasta med pesto","Lunch", "Stek kyckling i tunna skivor och " +
                "koka pastan lägg på peston på pastan när den serverats \n detta är en ny line " ,
                null,"Me",1,30,ingredientList,picture);
        recipeDBHandler.saveRecipe(recipe);

        ingredientList.clear();
        ingredientList.add(new Ingredient(new Grocery("Kycklingfile","kyckling"),"gram",500));
        ingredientList.add(new Ingredient(new Grocery("Lasangeplattor","pasta"),"tills de räcker",0));
        ingredientList.add(new Ingredient(new Grocery("Spenat","grönsak"),"gram",200));

        Recipe recipe1 = new Recipe("Kyckling spenat lasagnge","Middag","Jadu gör receptet",
                null,"Idk",4,60,ingredientList,picture);
        recipeDBHandler.saveRecipe(recipe1);

        addIngrediens();
        addPressureCookedRibs();


        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName("PopulateDB");

        ingredientList.clear();
        ingredientList.add(new Ingredient(new Grocery("Kycklingfile","kyckling"),"gram",500));
        ingredientList.add(new Ingredient(new Grocery("Lasangeplattor","pasta"),"tills de räcker",0));
        ingredientList.add(new Ingredient(new Grocery("Spenat","grönsak"),"gram",200));
        shoppingList.setIngredientList(ingredientList);

        listDBHandler.saveShoppingList(shoppingList);
    }


    private void addIngrediens(){
        try{
            File file = new File("src\\main\\resources\\ingredienser.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] grocery = scanner.nextLine().split(":");
                groceryDBHandler.saveGrocery(new Grocery(grocery[0],grocery[1]));
            }
        }catch(FileNotFoundException e){
            System.err.println("Error:" + e);
        }

    }


    private void addPressureCookedRibs() throws IOException {
        List<Ingredient> ingredientList = new ArrayList<>();

        ingredientList.add(new Ingredient(new Grocery("Tunna revben spjäl","fläsk"),"st",2));
        ingredientList.add(new Ingredient(new Grocery("Ketchup","sås"),"gram",100));
        ingredientList.add(new Ingredient(new Grocery("Honung","bak"),"gram",75));
        ingredientList.add(new Ingredient(new Grocery("Srirscha","sås"),"matsked",2));
        ingredientList.add(new Ingredient(new Grocery("Japansk soja","sås"),"matsked",2));
        ingredientList.add(new Ingredient(new Grocery("Brunt socker","bak"),"matsked",1));
        ingredientList.add(new Ingredient(new Grocery("Vitlöks pulver","krydda"),"matsked",1));
        ingredientList.add(new Ingredient(new Grocery("Five spice","krydda"),"mastsked",2));
        ingredientList.add(new Ingredient(new Grocery("Svart peppar","krydda"),"matsked",1));
        ingredientList.add(new Ingredient(new Grocery("Salt","krydda"),"mastsked",0.5));

        StringBuilder builder = new StringBuilder("Skölj och torka dina revben. Trimma överflödigt fett och ta bort membranet. " +
                "Skär ställen i tredjedelar eller fjärdedelar så att de passar in i tryckkokaren. Blanda ihop ketchup, honung, " +
                "sriracha och sojasås. Gnid den våta blandningen på revbenen.").append(System.lineSeparator())
                .append("Blanda ihop brunt socker, vitlökspulver, fem kryddpulver, svartpeppar och saltet. Gnid den torra blandningen på revbenen." +
                        " Lämna revbenen i kylskåp för att marinera i några timmar, eller över natten, om du kan, eftersom resultaten blir så" +
                        " mycket bättre.").append(System.lineSeparator())
                .append("När du är redo att laga mat, häll apelsinjuice, vatten och risvin i en tryckkokare. Skrapa i överflödig ketchupmarinad. " +
                        "Ställ revbenen på ändarna i en tryckkokare, sätt på locket och koka i 30 minuter vid högt tryck. Efter 30 minuter, släpp " +
                        "trycket och ta bort locket.").append(System.lineSeparator())
                .append("Överför revbenen till en tallrik och reducera såsen i pannan (utan lock) i 10 minuter tills den är tillräckligt tjock för " +
                        "att täcka en sked utan att springa av.").append(System.lineSeparator())
                .append("Förvärm grillen till hög och koka upp en vattenpanna. Lägg revbenen på ett rack över en bricka fodrad med folie " +
                        "och sked över den reducerade såsen. Grill i några minuter på varje sida tills de börjar skarpa och svarta. " +
                        "Värm upp nudlarna i 5 minuter (eller följ instruktionerna på paketet) i en wok med lite olja eller enligt " +
                        "paketinstruktionerna. Kasta med såsen.").append(System.lineSeparator())
                .append("Skiv vårlök och chili. Platta upp nudlarna med ett par kvartsställ per tallrik. Strö över vårlök och chili (efter smak!) " +
                        "Och servera! Serverar 4.");

        File file = new File("src/main/resources/IMG_1590.JPG");
        byte[] fileContentByte = Files.readAllBytes(file.toPath());

        String fileContent = "data:image/jpg;base64,";

        fileContent = fileContent + new String(Base64.getEncoder().encode(fileContentByte), StandardCharsets.UTF_8);

        Picture picture = new Picture(fileContent);


        Recipe recipe = new Recipe("Tryckt kokta asiatiska revbenspjäll","Middag",builder.toString(),null,
                "Sorted Food",4,60,ingredientList,picture);
        recipeDBHandler.saveRecipe(recipe);

    }

}
