package br.com.tech4me.music.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.music.model.Musica;
import br.com.tech4me.music.repository.MusicaRepository;
import br.com.tech4me.music.shared.MusicaDto;

@Service
public class MusicaServiceImpl implements MusicaService {

    @Autowired
    private MusicaRepository repositorio;

    
    @Override
    public List<MusicaDto> obterMusicas() {
        List<Musica> musicas = repositorio.findAll();

    
        return musicas.stream()
            .map(musica -> new ModelMapper().map(musica, MusicaDto.class))
            .collect(Collectors.toList());
    }
    
    @Override
    public Optional<MusicaDto> obterMusica(String id) {
        Optional<Musica> musica = repositorio.findById(id);

        if(musica.isPresent()){
            return Optional.of(new ModelMapper().map(musica.get(), MusicaDto.class));
        }

        return Optional.empty();
    }

    @Override
    public MusicaDto criarMusica(MusicaDto musica) {
        ModelMapper mapper = new ModelMapper();
        Musica musicaEntidade = mapper.map(musica, Musica.class);
        musicaEntidade = repositorio.save(musicaEntidade);
        
        return mapper.map(musicaEntidade, MusicaDto.class);
    }

    @Override
    public MusicaDto atualizarMusica(String id, MusicaDto musica) {
        Optional<Musica> musicaDesatualizada = repositorio.findById(id);
        ModelMapper mapper = new ModelMapper();
        if(musicaDesatualizada.isPresent())
            musicaDesatualizada.get().setTitulo(musica.getTitulo());
            musicaDesatualizada.get().setAlbum(musica.getAlbum());
            musicaDesatualizada.get().setAnoLancamento(musica.getAnoLancamento());
            musicaDesatualizada.get().setCompositor(musica.getCompositor());
            musicaDesatualizada.get().setGenero(musica.getGenero());
        
        return mapper.map(musicaDesatualizada, MusicaDto.class);
    }

    @Override
    public void deletarMusica(String id) {
        repositorio.deleteById(id);
    }

}
