package com.work.veloclub.service.news;

import com.work.veloclub.model.news.News;
import com.work.veloclub.model.news.NewsDTO;
import com.work.veloclub.model.news.NewsPostDTO;
import com.work.veloclub.model.news.NewsUpdateRequest;
import com.work.veloclub.model.news_metainfo.NewsMetaInfo;
import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.role.RolesEnum;
import com.work.veloclub.model.user_profile.UserProfile;
import com.work.veloclub.repository.news.NewsRepository;
import com.work.veloclub.repository.role.RoleRepository;
import com.work.veloclub.service.user.UserService;
import com.work.veloclub.util.Base64Util;
import com.work.veloclub.util.ContentUtil;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
import com.work.veloclub.util.exception_handler.error_message.ErrorMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .metaInfo(newsPostDTO.newsMetaInfoCreateRequests() != null ? newsPostDTO.newsMetaInfoCreateRequests()
                        .stream()
                        .map(newsMetaInfoCreateRequest -> new NewsMetaInfo(null, newsMetaInfoCreateRequest.name(),
                                Base64Util.decoder(newsMetaInfoCreateRequest.file()),
                                newsMetaInfoCreateRequest.isCore())).collect(Collectors.toSet()) : null)
                .build();
        if (newsPostDTO.recipientCategory() != RolesEnum.ANY && !newsPostDTO.recipients().isEmpty()) {
            for (Long userProfileId :
                    newsPostDTO.recipients()) {
                news.addProfile(UserProfile.builder().id(userProfileId).build());
            }
        }
        newsRepository.save(news);
        return news;
    }

    @Override
    public News updateNews(NewsUpdateRequest newsUpdateRequest) {
        Set<RoleEnum> roleEnumSet = new HashSet<>();
        switch (newsUpdateRequest.recipientCategory()) {
            case ANY -> {
                roleEnumSet.add(RoleEnum.SPORTSMAN);
                roleEnumSet.add(RoleEnum.DABBLER);
            }
            case SPORTSMAN -> roleEnumSet.add(RoleEnum.SPORTSMAN);
            case DABBLER -> roleEnumSet.add(RoleEnum.DABBLER);
        }


        News news = News.builder()
                .title(newsUpdateRequest.title())
                .content(newsUpdateRequest.content())
                .availableRoles(roleRepository.findRolesByNameIn(roleEnumSet))
                .metaInfo(newsUpdateRequest.newsMetaInfoUpdateRequests() != null ? newsUpdateRequest.newsMetaInfoUpdateRequests()
                        .stream()
                        .map(newsMetaInfoUpdateRequest -> new NewsMetaInfo(null, newsMetaInfoUpdateRequest.name(),
                                Base64Util.decoder(newsMetaInfoUpdateRequest.file()),
                                newsMetaInfoUpdateRequest.isCore())).collect(Collectors.toSet()) : null)
                .build();
        if (newsUpdateRequest.recipientCategory() != RolesEnum.ANY && !newsUpdateRequest.recipients().isEmpty()) {
            for (Long userProfileId :
                    newsUpdateRequest.recipients()) {
                news.addProfile(UserProfile.builder().id(userProfileId).build());
            }
        }
        newsRepository.save(news);
        return news;
    }

    @Override
    public NewsDTO getNews(Long id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new ObjectNotFound(ErrorMessages.NewsErrors.NOT_FOUND));
        return NewsDTO.builder().id(news.getId()).title(news.getTitle()).content(news.getContent()).createdDate(news.getCreatedDate()).updatedDate(news.getUpdatedDate()).build();
    }

    @Override
    public List<News> getNews() {
        return newsRepository.findAllWithMetaInfo(Sort.by("createdDate").descending()).stream().peek(n -> n.setContent(ContentUtil.getPreview(n.getContent()))).toList();
    }

    @Override
    public List<News> getNews(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<News> news = newsRepository.findAllWithMetaInfo(pageable);
        return news.getContent().stream().peek(n -> n.setContent(ContentUtil.getPreview(n.getContent()))).toList();
    }

    @Override
    public void validateCreateNewsContent(NewsPostDTO eventPostDto) {
        //TODO пока не ясно как обработать роли, найти решение при возникновении прецедента

//        if (eventPostDto.getAddRecipient() != null)
//            userService.userExistValidator(new HashSet<>(eventPostDto.getAddRecipient()));
    }


}
