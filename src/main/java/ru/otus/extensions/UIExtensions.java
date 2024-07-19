package ru.otus.extensions;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import ru.otus.modules.GuiceContentsModule;
import ru.otus.modules.GuiceDriverModule;
import java.util.Arrays;

public class UIExtensions implements BeforeEachCallback, AfterEachCallback {

    @Inject
    private WebDriver driver;

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        GuiceDriverModule guiceDriverModule = new GuiceDriverModule();
        Injector driverInjector = Guice.createInjector(guiceDriverModule);

        driverInjector.injectMembers(this);

        GuiceContentsModule guiceContentsModule = new GuiceContentsModule();

        this.injectDriverToModules(driverInjector, guiceContentsModule, guiceContentsModule);
        Injector fullInjector = Guice.createInjector(guiceDriverModule, guiceContentsModule);

        extensionContext.getTestInstance().ifPresent(instance -> {
            fullInjector.injectMembers(instance);
        });
        extensionContext.getTestInstance().ifPresent(instance -> {
            Arrays.stream(instance.getClass().getDeclaredFields())
                    .map(f -> f.getType())
                    .forEach(c -> fullInjector.injectMembers(fullInjector.getProvider(c).get()));
        });
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        extensionContext.getTestInstance()
                .ifPresent(instance -> {
                    if (driver != null) {
                        driver.quit();
                    }
                });
    }

    private void injectDriverToModules(Injector driverInjector, Module... modules) {
        for (Module module : modules) {
            driverInjector.injectMembers(module);
        }
    }
}
