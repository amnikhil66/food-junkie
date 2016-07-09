package com.foodjunkie.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Iterator;

public class FoodJunkie extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	//Splash Screen
//	private Texture splashScreen;

	//Misc
    private Array<Texture> foodContainer;
	private Array<Rectangle> foodItems;
	private long lastDropTime;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		batch = new SpriteBatch();

		camera.setToOrtho(false, 800, 400);

        foodContainer = new Array<Texture>();

		//Image Definitions
        addFoodToContainer(new Texture(Gdx.files.internal("drawable-xxxhdpi/broccoli.png")));
        addFoodToContainer(new Texture(Gdx.files.internal("drawable-xxxhdpi/carrot.png")));
        addFoodToContainer(new Texture(Gdx.files.internal("drawable-xxxhdpi/burger_01.png")));
        addFoodToContainer(new Texture(Gdx.files.internal("drawable-xxxhdpi/pizza_01.png")));

        //Food Items Array
        foodItems = new Array<Rectangle>();
        spawnFooItems();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(155, 214, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

//		batch.draw(splashScreen, 0, 0);
        for(Rectangle foodConfig : foodItems){
            batch.draw(foodContainer.random(), foodConfig.x, foodConfig.y);
        }

        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnFooItems();

        Iterator<Rectangle> iter = foodItems.iterator();
        while (iter.hasNext()){
            Rectangle foodItem = iter.next();
            foodItem.y -= 200 * Gdx.graphics.getDeltaTime();
            //TODO: Detect that the bottom is full
        }

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
        disposeFood();
	}

    private void addFoodToContainer(Texture foodItem){
        foodContainer.add(foodItem);
    }

	private void spawnFooItems(){
		Rectangle foodItem = new Rectangle();

		foodItem.x = MathUtils.random(0, 800 - 64);
		foodItem.y = 400 - 64;
		foodItem.height = 64;
		foodItem.width = 64;

		foodItems.add(foodItem);
		lastDropTime = TimeUtils.nanoTime();
	}

    private void disposeFood(){
        for (Texture foodItem : foodContainer) {
            foodItem.dispose();
        }
    }

}
