package by.itminsk.cyclingclubbackend.news;

import by.itminsk.cyclingclubbackend.news.dto.NewsDTO;
import by.itminsk.cyclingclubbackend.user.User;
import by.itminsk.cyclingclubbackend.user.UserRepository;
import by.itminsk.cyclingclubbackend.util.exception_handler.ObjectNotFound;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;

    private final UserRepository userRepository;

    @Override
    public NewsDTO getNews(Long id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Новость не найдена."));
        return NewsDTO.builder().id(news.getId()).title(news.getTitle()).content(news.getContent()).creationDate(news.getCreationDate()).build();
    }

    @Override
    public List<NewsDTO> getNews() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        //TODO Подлежит оптимизации.
        User user = userRepository.findUserByPhoneNumber(currentPrincipalName).orElseThrow(() -> new ObjectNotFound("Пользователь не найден."));
        return  user.getNews().stream()
                .map(news -> NewsDTO.builder().id(news.getId()).title(news.getTitle())
                        .content(String.join(" ",List.of(StringUtils.split(news.getContent()," ")).subList(0, 20)))
                        .creationDate(news.getCreationDate())
                        .build()).sorted(Comparator.comparing(NewsDTO::getCreationDate).reversed()).collect(Collectors.toList());
    }
}
