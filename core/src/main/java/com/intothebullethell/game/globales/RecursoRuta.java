package com.intothebullethell.game.globales;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RecursoRuta {
    
    //JUGADOR
    public static final TextureRegion SPRITE_ARRIBA_1 = new TextureRegion(new Texture("imagenes/personaje/personajeArriba1.png"));
    public static final TextureRegion SPRITE_ARRIBA_2 = new TextureRegion(new Texture("imagenes/personaje/personajeArriba2.png"));
    
    public static final TextureRegion SPRITE_ABAJO_1 = new TextureRegion(new Texture("imagenes/personaje/personajeAbajo1.png"));
    public static final TextureRegion SPRITE_ABAJO_2 = new TextureRegion(new Texture("imagenes/personaje/personajeAbajo2.png"));
    
    public static final TextureRegion SPRITE_IZQUIERDA_1 = new TextureRegion(new Texture("imagenes/personaje/personajeIzquierda1.png"));
    public static final TextureRegion SPRITE_IZQUIERDA_2 = new TextureRegion(new Texture("imagenes/personaje/personajeIzquierda2.png"));
    
    public static final TextureRegion SPRITE_DERECHA_1 = new TextureRegion(new Texture("imagenes/personaje/personajeDerecha1.png"));
    public static final TextureRegion SPRITE_DERECHA_2 = new TextureRegion(new Texture("imagenes/personaje/personajeDerecha2.png"));
    
    //BENGALA
    public static final Texture BENGALA = new Texture("imagenes/otros/hud/bengala.png");
    
    //ENEMIGOS
    public static final Texture SPRITE_ROBOT_1 = new Texture("imagenes/enemigos/robot1.png");  
    public static final Texture SPRITE_ROBOT_2 = new Texture("imagenes/enemigos/robot2.png");  
    
    public static final Texture SPRITE_SLIME_1 = new Texture("imagenes/enemigos/slime1.png");  
    public static final Texture SPRITE_SLIME_2 = new Texture("imagenes/enemigos/slime2.png");  
    
    public static final Texture SPRITE_MEDUSA_1 = new Texture("imagenes/enemigos/medusa1.png");  
    public static final Texture SPRITE_MEDUSA_2 = new Texture("imagenes/enemigos/medusa2.png");  
    
    public static final Texture PROYECTIL_ENEMIGO_1 = new Texture("imagenes/objetos/armaProyectil/bala_enemigo_1.png");
    public static final Texture PROYECTIL_ENEMIGO_2 = new Texture("imagenes/objetos/armaProyectil/bala_enemigo_2.png");
    
    //ARMAS
    public static final Texture ARMA_PISTOLA = new Texture("imagenes/objetos/armas/pistola.png");
    public static final Texture ARMA_ESCOPETA = new Texture("imagenes/objetos/armas/escopeta.png");
    public static final Texture ARMA_AWP = new Texture("imagenes/objetos/armas/awp.png");
    public static final Texture ARMA_BGF9000= new Texture("imagenes/objetos/armas/bfg9000.png");
    public static final Texture ARMA_BLASTER = new Texture("imagenes/objetos/armas/blaster.png");
    public static final Texture ARMA_ESTRELLA = new Texture("imagenes/objetos/armas/estrella.png");
    public static final Texture ARMA_SNIPER= new Texture("imagenes/objetos/armas/sniper.png");
    
    //OBJETOS AGARRABLES
    public static final Texture CAJA_VIDA= new Texture("imagenes/objetos/objetosAgarrables/caja_vida.png");
    public static final Texture CAJA_MUNICION= new Texture("imagenes/objetos/objetosAgarrables/caja_municion.png");
    
    //OBJETOS ACTIVOS
    public static final Texture ADRENALINA = new Texture("imagenes/objetos/objetosActivos/adrenalina.png");
    public static final Texture SANGUCHE = new Texture("imagenes/objetos/objetosActivos/sanguche.png");
    public static final Texture ANILLO_UNICO = new Texture("imagenes/objetos/objetosActivos/anillo_unico.png");
    
    //PROYECTILES
    public static final Texture PROYECTIL_PISTOLA = new Texture("imagenes/objetos/armaProyectil/bala_pequeña.png");
    
    public static final Texture PROYECTIL_ESCOPETA = new Texture("imagenes/objetos/armaProyectil/bala_grande.png");
    
    public static final Texture PROYECTIL_BFG9000_1 = new Texture("imagenes/objetos/armaProyectil/bala_bfg_1.png");
    public static final Texture PROYECTIL_BFG9000_2 = new Texture("imagenes/objetos/armaProyectil/bala_bfg_2.png");
    
    public static final Texture PROYECTIL_SNIPER = new Texture("imagenes/objetos/armaProyectil/bala_sniper.png");
    
    public static final Texture PROYECTIL_AWP = new Texture("imagenes/objetos/armaProyectil/bala_sniper.png");
    
    public static final Texture PROYECTIL_ESTRELLA_1 = new Texture("imagenes/objetos/armaProyectil/bala_estrella_1.png");
    public static final Texture PROYECTIL_ESTRELLA_2 = new Texture("imagenes/objetos/armaProyectil/bala_estrella_2.png");
    
    public static final Texture PROYECTIL_BLASTER_1 = new Texture("imagenes/objetos/armaProyectil/bala_blaster_1.png");
    public static final Texture PROYECTIL_BLASTER_2 = new Texture("imagenes/objetos/armaProyectil/bala_blaster_2.png");
        
	//HUD
    public static final Texture CORAZON_LLENO = new Texture("imagenes/otros/hud/fullHeart.png");
    public static final Texture CORAZON_MITAD = new Texture("imagenes/otros/hud/halfHeart.png");
    public static final Texture CORAZON_VACIO = new Texture("imagenes/otros/hud/blankHeart.png");
    
    
    public static void dispose() {
        CORAZON_LLENO.dispose();
        CORAZON_MITAD.dispose();
        CORAZON_VACIO.dispose();
    }
}
