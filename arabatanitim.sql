drop database if exists arabatanitim ;
create database if not exists arabatanitim;

use  arabatanitim;

create table motor(
    id int primary key auto_increment,
    adi varchar(20),
    hacmi int,
    gucu int
);
create table dosya(
    id int primary key auto_increment,
    adi varchar(500),
    dosya_yolu varchar(500)
);

create table yakit(
    id int primary key auto_increment,
    adi varchar(20),
    tuketim int,
    miktar int
);
create table mensei(
    id int primary key auto_increment,
    adi varchar(20)
);
create table kasa(
    id int primary key auto_increment,
    adi varchar(20)

);
create table yil(
    id int primary key auto_increment,
    adi varchar(20)
);

create table vites(
    id int primary key auto_increment,
    adi varchar(20)
);
create table fiyat(
    id int primary key auto_increment,
    adi varchar(20),
    deger int

);
create table marka(
    id int primary key auto_increment,
    adi varchar(20),
    mensei_id int,
    logo_id int,
     FOREIGN KEY (mensei_id) REFERENCES mensei(id) ON DELETE SET NULL,
     FOREIGN KEY (logo_id) REFERENCES dosya(id) ON DELETE SET NULL
);
create table aciklama(
    id int primary key auto_increment,
    adi varchar(20),
    icerik varchar(600)
);

create table yetki(
    id int primary key auto_increment,
    adi varchar(50),
    admin boolean,
    moderator boolean,
    normal boolean
);
create table user(
    id int primary key auto_increment,
    username varchar(50),
    password varchar(50),
        yetki_id int,
    FOREIGN KEY (yetki_id) REFERENCES yetki(id) ON DELETE SET NULL
);

-- insert into model(adi,renk,marka_id,yakit_id,fiyat_id,kasa_id,motor_id,vites_id,yil_id,model_id))

create table model(
    id int primary key auto_increment,
    adi varchar(20),
    renk varchar(20),
    marka_id int,
    yakit_id int,
    fiyat_id int,
    kasa_id int,
    motor_id int,
    vites_id int,
    yil_id int,

    FOREIGN KEY (marka_id) REFERENCES marka(id) ON DELETE SET NULL,
    FOREIGN KEY (yakit_id) REFERENCES yakit(id) ON DELETE SET NULL,
    FOREIGN KEY (fiyat_id) REFERENCES fiyat(id) ON DELETE   SET NULL,
    FOREIGN KEY (kasa_id) REFERENCES kasa(id) ON DELETE     SET NULL,
    FOREIGN KEY (motor_id) REFERENCES motor(id) ON DELETE SET NULL,
    FOREIGN KEY (yil_id) REFERENCES yil(id) ON DELETE SET NULL,
    FOREIGN KEY (vites_id) REFERENCES vites(id) ON DELETE SET NULL

);
create table model_aciklama(
    model_id int,
    aciklama_id int,
    unique (model_id,aciklama_id),
    FOREIGN KEY (model_id) REFERENCES model(id)  ON DELETE SET NULL,
    FOREIGN KEY (aciklama_id) REFERENCES aciklama(id)  ON DELETE SET NULL
);

