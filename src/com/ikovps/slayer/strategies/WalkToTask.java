package com.ikovps.slayer.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.TilePath;

import com.ikovps.slayer.data.Variables;

/**
 * A class that teleports to an assigned Slayer task.
 * @author Masood
 * 
 */
public class WalkToTask extends Variables implements Strategy {
	
	@Override
	public boolean activate() {
		return teleported && !arrived;
	}

	@Override
	public void execute() {
		
		if (task.getObject() > 0 && !clickObstacle) {
			
			final SceneObject[] toClick = SceneObjects.getNearest(task.getObject());
			
			if (toClick.length > 0 && toClick != null) {
				
				try {
					
					toClick[0].interact(0);
					
					Time.sleep(new SleepCondition() {
						@Override
						public boolean isValid() {
							return toClick.length < 1;
						}
					}, 2000);
					
				} catch (ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
				clickObstacle = true;
			}
		} else {
			
			clickObstacle = true;
		}
		
		if (clickObstacle) {
			
			TilePath toMonster = new TilePath(task.getTiles());
			
			if (toMonster != null) {
				toMonster.traverse();
				Time.sleep(1000);
			}
			
			if (toMonster.hasReached() && !task.isSecondFloor()) {
				arrived = true;
			} else {
				
			}
		}
	}
}
