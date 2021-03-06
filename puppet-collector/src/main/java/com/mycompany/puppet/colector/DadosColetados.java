/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.puppet.colector;

import com.github.britooo.looca.api.group.memoria.Memoria;
import java.util.Date;
import oshi.SystemInfo;
import oshi.software.os.FileSystem;
import oshi.software.os.OperatingSystem;

/**
 *
 * @author deusf
 */
public class DadosColetados {
  
    private Integer id;
    private Integer fkMaquinaVirtual;
    private Double usoDisco;
    private Double usoRam;
    private Double usoProcessador;
    private Date dataHora;

    
    public void coleta(){
//        API Looca
        Memoria memoria = new Memoria();

//        API OSHI
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        FileSystem file = operatingSystem.getFileSystem();

//        Calculo de disco
        Long total = file.getFileStores().get(0).getTotalSpace();
        Long disponivel = file.getFileStores().get(0).getFreeSpace();
        Double porcentagemDisco = (total.doubleValue() - disponivel.doubleValue());

        Double usoDiscoConvertido = Math.round(porcentagemDisco * 100.0) / 100.0;
        System.out.println(usoDiscoConvertido);

        Double memoriaUso = memoria.getEmUso().doubleValue();
        Double memoriaTotal = memoria.getTotal().doubleValue();

        Double usoMemoria = (memoriaUso / memoriaTotal) * 100.0;

        System.out.println("disco disponivel: " + disponivel);
        System.out.println("disco Total: " + total);
        System.out.println("Porcentagem: " + porcentagemDisco);      
        
    }

    public DadosColetados(MaquinaVirtual vm) {
        this.fkMaquinaVirtual = vm.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkMaquinaVirtual() {
        return fkMaquinaVirtual;
    }

    public void setFkMaquinaVirtual(Integer fkMaquinaVirtual) {
        this.fkMaquinaVirtual = fkMaquinaVirtual;
    }

    public Double getUsoDisco() {
        return usoDisco;
    }

    public void setUsoDisco(Double usoDisco) {
        this.usoDisco = usoDisco;
    }

    public Double getUsoRam() {
        return usoRam;
    }

    public void setUsoRam(Double usoRam) {
        this.usoRam = usoRam;
    }

    public Double getUsoProcessador() {
        return usoProcessador;
    }

    public void setUsoProcessador(Double usoProcessador) {
        this.usoProcessador = usoProcessador;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }
    
    
}
