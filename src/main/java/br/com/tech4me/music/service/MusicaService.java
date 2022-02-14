package br.com.tech4me.music.service;

import java.util.*;
import br.com.tech4me.music.shared.MusicaDto;

public interface MusicaService {
    
    List<MusicaDto> obterMusicas();

    Optional<MusicaDto> obterMusica(String id); 
    
    MusicaDto criarMusica(MusicaDto musica);

    MusicaDto atualizarMusica(String id, MusicaDto musica);
    
    void deletarMusica(String id);

}
