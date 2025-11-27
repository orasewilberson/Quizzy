package com.example.quizzy.data.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionData {

    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        // Math
        questions.add(new Question( "Combien font 2 + 2 ?", "3", "8", "5", "4", "Mathematiques"));
        questions.add(new Question("Quelle est la racine carrée de 16 ?", "2", "3", "5", "4", "Mathematiques"));
        questions.add(new Question("Quelle est la vraie reponse -5-7 ?", "12", "7", "5", "-12", "Mathematiques"));
        questions.add(new Question( "Si x = 7, combien vaut 3x + 5 ?", "15", "21", "28", "26", "Mathematiques"));
        questions.add(new Question("Quel est le plus grand diviseur commun de 36 et 48 ?", "6", "9", "18", "12", "Mathematiques"));
        questions.add(new Question("Combien y a-t-il de nombres premiers entre 1 et 20 ?", "7", "12", "6", "8", "Mathematiques"));
        questions.add(new Question( "Quel est le reste de 57 / 4 ?", "3", "0", "2", "1", "Mathematiques"));
        questions.add(new Question("Si un nombre est divisible par 6, il est aussi divisible par ?", "2 et 4", "3 et 4", "2 et 5", "2 et 3", "Mathematiques"));
        questions.add(new Question("Quel est le résultat de 5! ?", "130", "5", "-120", "120", "Mathematiques"));
        questions.add(new Question("Combien de côtés a un dodécagone ?", "8", "10", "2", "12", "Mathematiques"));

        // Chimie
        questions.add(new Question("Quelle est la formule chimique de l’eau ?", "H0₂", "CO₂", "O₂", "H₂O", "Chimie"));
        questions.add(new Question("Quel est le pH d’une solution neutre ?", "0", "8", "14", "7", "Chimie"));
        questions.add(new Question("Quel est le gaz le plus abondant dans l’atmosphère terrestre ?", "Oxygène", "Dioxyde de carbone", "Carbone", "Azote", "Chimie"));
        questions.add(new Question("Quel métal est liquide à température ambiante ?", "Fer", "Calcium", "Aluminium", "Mercure", "Chimie"));
        questions.add(new Question("Quel élément chimique a pour symbole Fe ?", "Acier", "Fluor", "Francium", "Fer", "Chimie"));
        questions.add(new Question("Quelle est la charge électrique d'un neutron ?", "Positive", "aucun", "Négative", "Neutre", "Chimie"));
        questions.add(new Question("Quel est le nombre atomique de l’hydrogène ?", "3", "2", "8", "1", "Chimie"));
        questions.add(new Question("Quel est le principal constituant du sel de table ?", "Chlore", "Sulfate de calcium", "Nitrate de potassium", "Chlorure de sodium", "Chimie"));
        questions.add(new Question("Quel type de réaction chimique libère de l’énergie sous forme de chaleur ?", "Esothermique", "Endothermique", "Photocatalytique", "Exothermique", "Chimie"));
        questions.add(new Question("Comment appelle-t-on la transformation d’un solide en gaz sans passer par l’état liquide ?", "Vaporisation", "Évaporation", "Fusion", "Sublimation", "Chimie"));

        //Biologie
        questions.add(new Question("Quel organe humain est responsable de la production d’insuline ?", "Rein", "Salive", "Foie", "Pancréas", "Biologie"));
        questions.add(new Question("Quel est l’organe le plus grand du corps humain ?", "Cerveau", "Foie", "Rein", "Peau", "Biologie"));
        questions.add(new Question("Quel est le groupe sanguin universellement donneur ?", "A+", "B-", "AB+", "O-", "Biologie"));
        questions.add(new Question("Quelle est la fonction principale des globules rouges ?", "Défendre l’organisme", "Importer l’oxygène", "Digérer les aliments", "Transporter l’oxygène", "Biologie"));
        questions.add(new Question("Quel est l’élément principal qui compose les os ?", "Fer", "Calorie", "Potassium", "Calcium", "Biologie"));
        questions.add(new Question("Quelle molécule porte l’information génétique ?", "ARN", "ADRN", "Protéine", "ADN", "Biologie"));
        questions.add(new Question("Combien de chromosomes possède un être humain ?", "23", "56", "48", "46", "Biologie"));
        questions.add(new Question("Quelle est la partie du cerveau responsable de la coordination des mouvements ?", "Cerveau", "Crane", "Hypothalamus", "Cervelet", "Biologie"));
        questions.add(new Question("Quel gaz les plantes absorbent-elles pour la photosynthèse ?", "Oxygène", "oxyde de carbone", "Azote", "Dioxyde de carbone", "Biologie"));
        questions.add(new Question("Quel organe est principalement responsable de la filtration du sang ?", "Foie", "vein", "Rate", "Rein", "Biologie"));

        // Culture generale
        questions.add(new Question("Quelle est la capitale de la France ?", "Marsey", "Londres", "Berlin", "Paris", "Culture generale"));
        questions.add(new Question("En quelle année a eu lieu la Révolution française ?", "1804", "1879",  "1748", "1789","Culture generale"));
        questions.add(new Question("Qui a découvert l’Amérique en 1492 ?", "Vasco de Gama", "Marco Polo", "Magellan", "Christophe Colomb", "Culture generale"));
        questions.add(new Question("Qui était le premier président des États-Unis ?", "Abraham Lincoln", "Thomas Jefferson",  "John Adams", "George Washington","Culture generale"));
        questions.add(new Question("Quel pays a construit la Grande Muraille ?", "Mongolie", "Japon", "Inde", "Chine", "Culture generale"));
        questions.add(new Question("Qui a écrit L’Art de la guerre ?", "Napoléon", "Machiavel",  "Confucius", "Sun Tzu","Culture generale"));
        questions.add(new Question("Dans quel pays est né le jeu d’échecs ?", "Grèce", "Egypte", "chine", "Inde", "Culture generale"));
        questions.add(new Question("Quel est le plus long fleuve du monde ?", "Yangtsé", "Mississippi",  "Nil", "Amazonie","Culture generale"));
        questions.add(new Question("Quel pays a la plus grande population ?", "Etats-Unis", "Russie", "Canada", "Chine", "Culture generale"));
        questions.add(new Question("Qui a été le premier homme à marcher sur la Lune ?", "Buzz Aldrin", "Michael Collins",  "Yuri Gagarin", "Neil Armstrong","Culture generale"));

        //Divertissement et Sport
        questions.add(new Question("Quel est le sport le plus pratiqué au monde ?", "Basket-ball", "Volley-Ball", "Tennis", "Football", "Divertissement et Sport"));
        questions.add(new Question("Dans quel pays est né le jeu d’échecs ?", "Chine", "Grèce", "Italie", "Inde", "Divertissement et Sport"));
        questions.add(new Question("Qui a remporté le plus grand nombre de Ballon d’Or ?", "Maradona", "Pelé", "Cristiano Ronaldo", "Lionel Messi", "Divertissement et Sport"));
        questions.add(new Question("Quel est le super-héros principal de Gotham City ?", "Rocker", "Superman", "Spider-Man", "Batman", "Divertissement et Sport"));
        questions.add(new Question("Qui détient le record du monde du 100 mètres masculin ?", "Cristiano Ronaldo", "Carl Lewis", "Justin Gatlin", "Usain Bolt", "Divertissement et Sport"));
        questions.add(new Question("Quel pays a remporté la Coupe du Monde de football 2018 ?", "Brésil", "Allemagne", "Argentine", "France", "Divertissement et Sport"));
        questions.add(new Question("Quel est le sport pratiqué lors du Tour de France ?", "Football", "Basket-ball", "Tennis", "Cyclisme", "Divertissement et Sport"));
        questions.add(new Question("Qui est considéré comme le meilleur joueur de tennis de tous les temps ?", "Rawan Quatar", "Rafael Nadal", "Novak Djokovic", "Roger Federer", "Divertissement et Sport"));
        questions.add(new Question("Dans quel sport Michael Jordan a-t-il été une légende ?", "Football", "Baseball", "Tennis", "Basket-ball", "Divertissement et Sport"));
        questions.add(new Question("Quel pays detient le plus grand nombre de coup du monde ?", "France", "Argentine", "Almagne", "Brazil", "Divertissement et Sport"));

        //Programmation
        questions.add(new Question("Quel est le rôle principal d'un compilateur ?", "Créer", "Rentabilite", "Optimiser", "Traduire", "Programmation"));
        questions.add(new Question("Première étape de développement ?", "Analyse", "Codage", "Tests", "Analyse", "Programmation"));
        questions.add(new Question("Qu'est-ce qu'une variable ?", "Reel", "Fonction", "Type de données", "Espace mémoire", "Programmation"));
        questions.add(new Question("Type de données pour un nombre flottant en C ?", "String", "int", "double", "float", "Programmation"));
        questions.add(new Question("Méthode pour ajouter un élément à une liste en Python ?", "apend()", "add()", "push()", "append()", "Programmation"));
        questions.add(new Question("Résultat de 10 % 3 en Python ?", "2", "3", "0", "1", "Programmation"));
        questions.add(new Question("Mot-clé pour une variable constante en JavaScript ?", "final", "def", "immutable", "const", "Programmation"));
        questions.add(new Question("Langage principalement utilisé en POO ?", "Python", "C++", "C", "Java", "Programmation"));
        questions.add(new Question("Objectif de la programmation fonctionnelle ?", "Créer objets", "Variables pures", "Structures de données", "Fonctions pures", "Programmation"));
        questions.add(new Question("Mot-clé pour une constante en C ?", "def", "final", "static", "const", "Programmation"));


        return questions;
    }
}
