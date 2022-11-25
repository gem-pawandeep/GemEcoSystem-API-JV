package com.qa.gemini;

import io.cucumber.core.cli.Main;
import org.testng.annotations.Test;

import java.util.stream.Stream;

public class main {
    @Test
    public void main(){
        String[] defaultOptions = {"--glue", "com.qa.gemini.StepDefinition", "--glue", "com.gemini.generic.bdd", "src/main/resources/features/GemEcoSystem.feature"};
        Stream<String> cucumberOptions = (Stream.of(defaultOptions));
        Main.main(cucumberOptions.toArray(String[]::new));
    }
}
