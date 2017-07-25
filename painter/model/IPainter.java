package painter.model;

import java.awt.Color;

public interface IPainter {

	Color[] colors = new Color[] {
		   Color.BLACK, new Color(127, 127, 127), new Color(136, 0, 21), Color.RED, new Color(255, 127, 39),		   
		   Color.YELLOW, new Color(34, 177, 76), new Color(0, 162, 232), new Color(63, 72, 204), 
		   new Color(163, 73, 164), Color.WHITE, new Color(195, 195, 195), new Color(185, 122, 87), Color.PINK, 
		   new Color(255, 201, 14), new Color(239, 228, 176), new Color(181, 230, 29), new Color(153, 217, 234), 
		   new Color(112, 146, 190), new Color(200, 191, 231)
		};
	
	String[] colorsToolTips = new String[] {
		"Черный", "Темно-серый", "Темно-красный", "Красный","Оранжевый", "Желтый", "Зеленый", "Бирюзовый",
		"Индиго", "Лиловый", "Белый", "Светло-серый", "Коричневый", "Розовый", "Золотистый", "Светло-желтый",
		"Травяной", "Светло-бирюзовый", "Сизый", "Сиреневый"
	};
	
	Color labelColorSelectedBackground = Color.ORANGE;
}
