package by.itminsk.cyclingclubbackend.service.news;

import by.itminsk.cyclingclubbackend.model.news.News;
import by.itminsk.cyclingclubbackend.model.news.NewsDTO;
import by.itminsk.cyclingclubbackend.model.news.NewsPostDTO;
import by.itminsk.cyclingclubbackend.repository.news.NewsRepository;
import by.itminsk.cyclingclubbackend.repository.role.RoleRepository;
import by.itminsk.cyclingclubbackend.model.user.User;
import by.itminsk.cyclingclubbackend.service.user.UserService;
import by.itminsk.cyclingclubbackend.util.exception_handler.ObjectNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    private final UserService userService;

    private final RoleRepository roleRepository;

    @Override
    public void createNews(NewsPostDTO newsPostDTO) {
        validateCreateNewsContent(newsPostDTO);
        News news = News.builder()
                .title(newsPostDTO.getTitle())
                .content(newsPostDTO.getContent())
                .availableRoles(roleRepository.findRolesByNameIn(newsPostDTO.getRecipientCategory().getRoleEnumSet()))
                .availableUsers(newsPostDTO.getAddRecipient() != null ? newsPostDTO.getAddRecipient().stream().map(recipient -> User.builder().id(recipient).build()).collect(Collectors.toSet()) : null)
                .creationDate(new Date())
                .build();
        newsRepository.save(news);
    }

    @Override
    public NewsDTO getNews(Long id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Новость не найдена."));
        return NewsDTO.builder().id(news.getId()).title(news.getTitle()).content(news.getContent()).creationDate(news.getCreationDate()).build();
    }

    @Override
    public List<NewsDTO> getNews(Integer page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findUserByPhoneNumber(currentPrincipalName);
        Pageable pageable = PageRequest.of(page, 10);
        return newsRepository.findAllByRoleOrUser(user.getRole().getId(), user.getId() ,pageable).stream()
                .peek(news -> news.setContent(String.join(" ", List.of(StringUtils.split(news.getContent(), " ")).subList(0, 20))))
                .collect(Collectors.toList());
    }

    @Override
    public void validateCreateNewsContent(NewsPostDTO eventPostDto) {
        //TODO пока не ясно как обработать роли, найти решение при возникновении прецедента

        if (eventPostDto.getAddRecipient() != null)
            userService.userExistValidator(new HashSet<>(eventPostDto.getAddRecipient()));
    }

    @Override
    public List<NewsDTO> getLatestNews() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findUserByPhoneNumber(currentPrincipalName);
        Pageable pageable = PageRequest.of(0, 3);
        return newsRepository.findAllByRoleOrUser(user.getRole().getId(), user.getId() ,pageable).stream()
                .peek(news -> news.setContent(String.join(" ", List.of(StringUtils.split(news.getContent(), " ")).subList(0, 20))))
                .collect(Collectors.toList());
    }
}