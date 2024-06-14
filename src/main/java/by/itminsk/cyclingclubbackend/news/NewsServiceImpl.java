package by.itminsk.cyclingclubbackend.news;

import by.itminsk.cyclingclubbackend.news.dto.NewsDTO;
import by.itminsk.cyclingclubbackend.news.dto.NewsPostDTO;
import by.itminsk.cyclingclubbackend.role.RoleRepository;
import by.itminsk.cyclingclubbackend.user.User;
import by.itminsk.cyclingclubbackend.user.UserRepository;
import by.itminsk.cyclingclubbackend.user.UserService;
import by.itminsk.cyclingclubbackend.util.exception_handler.ObjectNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    public List<NewsDTO> getNews() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findUserByPhoneNumber(currentPrincipalName);
        log.info("User Id : {}", user.getId());
        log.info("Role Id : {}", user.getRole().getId());
        return newsRepository.findAllByRoleOrUser(user.getRole().getId(), user.getId()).stream()
                .peek(news -> news.setContent(String.join(" ", List.of(StringUtils.split(news.getContent(), " ")).subList(0, 20))))
                .sorted(Comparator.comparing(NewsDTO::getCreationDate).reversed()).collect(Collectors.toList());
    }

    @Override
    public void validateCreateNewsContent(NewsPostDTO eventPostDto) {
        //TODO пока не ясно как обработать роли, найти решение при возникновении прецедента

        if (eventPostDto.getAddRecipient() != null)
            userService.userExistValidator(new HashSet<>(eventPostDto.getAddRecipient()));
    }
}
