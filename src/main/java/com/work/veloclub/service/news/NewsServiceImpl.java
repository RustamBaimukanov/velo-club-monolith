package com.work.veloclub.service.news;

import com.work.veloclub.model.news.News;
import com.work.veloclub.model.news.NewsDTO;
import com.work.veloclub.model.news.NewsPostDTO;
import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.role.RolesEnum;
import com.work.veloclub.model.user.User;
import com.work.veloclub.model.user_profile.UserProfile;
import com.work.veloclub.repository.news.NewsRepository;
import com.work.veloclub.repository.role.RoleRepository;
import com.work.veloclub.service.user.UserService;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    private final UserService userService;

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public News createNews(NewsPostDTO newsPostDTO) {
        //validateCreateNewsContent(newsPostDTO);
        Set<RoleEnum> roleEnumSet = new HashSet<>();
        switch (newsPostDTO.recipientCategory()) {
            case ANY -> {
                roleEnumSet.add(RoleEnum.SPORTSMAN);
                roleEnumSet.add(RoleEnum.DABBLER);
            }
            case SPORTSMAN -> roleEnumSet.add(RoleEnum.SPORTSMAN);
            case DABBLER -> roleEnumSet.add(RoleEnum.DABBLER);
        }


        News news = News.builder()
                .title(newsPostDTO.title())
                .content(newsPostDTO.content())
                .availableRoles(roleRepository.findRolesByNameIn(roleEnumSet))
                .createdAt(LocalDate.now())
                .build();
        if (newsPostDTO.recipientCategory() != RolesEnum.ANY && !newsPostDTO.recipients().isEmpty()) {
            for (Long userProfileId :
                    newsPostDTO.recipients()) {
                news.addProfile(UserProfile.builder().id(userProfileId).build());
            }
        }
        return news;
    }

    @Override
    public NewsDTO getNews(Long id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Новость не найдена."));
        return NewsDTO.builder().id(news.getId()).title(news.getTitle()).content(news.getContent()).createdAt(news.getCreatedAt()).build();
    }

    @Override
    public List<NewsDTO> getNews(Integer page) {
        return null;
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        User user = userService.findUserByPhoneNumber(currentPrincipalName);
//        Pageable pageable = PageRequest.of(page, 10);
//        return newsRepository.findAllByRoleOrUser(user.getRole().getId(), user.getId(), pageable).stream()
//                .peek(news -> news.setContent(String.join(" ", List.of(StringUtils.split(news.getContent(), " ")).subList(0, 20))))
//                .collect(Collectors.toList());
    }

    @Override
    public List<News> getNews(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<News> news = newsRepository.findAllWithMetaInfo(pageable);
        return news.getContent();
    }

    @Override
    public void validateCreateNewsContent(NewsPostDTO eventPostDto) {
        //TODO пока не ясно как обработать роли, найти решение при возникновении прецедента

//        if (eventPostDto.getAddRecipient() != null)
//            userService.userExistValidator(new HashSet<>(eventPostDto.getAddRecipient()));
    }


}
