package com.foodjunkie.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.foodjunkie.game.FoodJunkie;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(800, 400);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new FoodJunkie();
        }
}